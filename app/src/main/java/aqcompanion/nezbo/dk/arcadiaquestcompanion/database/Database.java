package aqcompanion.nezbo.dk.arcadiaquestcompanion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Campaign;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Hero;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Icon;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Player;
import aqcompanion.nezbo.dk.arcadiaquestcompanion.model.Quest;

/**
 * Created by Emil on 15-11-2015.
 */
public class Database {

    private final DBHelper helper;
    private SQLiteDatabase db;

    public Database(Context c) {
        this.helper = new DBHelper(c, "aqcompanion");
    }

    public void open() {
        this.db = helper.getWritableDatabase();
    }

    public void close() {
        this.db.close();
    }

    // Getters (static content)

    public List<Pair<Integer, String>> getBaseCampaigns() {
        Cursor cursor = db.query("campaign_static", new String[]{"_id", "title"}, null, null, null, null, null);
        ArrayList<Pair<Integer, String>> result = new ArrayList<Pair<Integer, String>>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            result.add(new Pair<Integer,String>(id, title));
        }
        cursor.close();

        return result;
    }

    // Getters (dynamic content

    public List<Campaign> getCampaigns() {
        ArrayList<Campaign> result = new ArrayList<>();
        for(int id : getCampaignIds()) {
            result.add(getCampaign(id));
        }
        return result;
    }

    public List<Integer> getCampaignIds() {
        Cursor cursor = db.query("campaign_play", new String[]{"_id"}, null, null, null, null, null);
        ArrayList<Integer> result = new ArrayList<Integer>();

        while(cursor.moveToNext()) {
            result.add(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        }
        cursor.close();

        return result;
    }

    public Campaign getCampaign(int id) {
        // Campaign Values
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM campaign_play " +
                "WHERE _id = ?"
                , new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        int baseid = cursor.getInt(cursor.getColumnIndexOrThrow("campaign_id"));
        boolean completed = cursor.getInt(cursor.getColumnIndexOrThrow("completed")) > 0;

        cursor.close();
        cursor = db.rawQuery("SELECT title " +
                            "FROM campaign_static " +
                            "WHERE _id = ?"
                            , new String[]{String.valueOf(baseid)});
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        cursor.close();

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Quest> quests = new ArrayList<>();
        Campaign result = new Campaign(baseid, id, title, players, quests, completed);

        // Load players
        cursor = db.rawQuery("SELECT * " +
                                    "FROM player " +
                                    "WHERE campaign_id = ?"
                                    , new String[]{String.valueOf(id)});
        while(cursor.moveToNext()) {
            int player_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String color = cursor.getString(cursor.getColumnIndexOrThrow("color"));
            boolean savedGold = cursor.getInt(cursor.getColumnIndexOrThrow("saved_gold")) > 0;
            ArrayList<Hero> heroes = new ArrayList<>();

            players.add(new Player(player_id, color, name, savedGold, heroes));
        }
        cursor.close();

        // Load heroes
        for(Player p : players) {
            List<Hero> herolist = p.getHeroes();

            cursor = db.rawQuery("SELECT * " +
                                "FROM hero_play " +
                                "WHERE player_id = ?"
                                , new String[]{String.valueOf(p.getId())});
            while(cursor.moveToNext()) {
                String[] cards = new String[4];
                int hero_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                cards[0] = cursor.getString(cursor.getColumnIndexOrThrow("card1"));
                cards[1] = cursor.getString(cursor.getColumnIndexOrThrow("card2"));
                cards[2] = cursor.getString(cursor.getColumnIndexOrThrow("card3"));
                cards[3] = cursor.getString(cursor.getColumnIndexOrThrow("card4"));
                String curse = cursor.getString(cursor.getColumnIndexOrThrow("curse"));

                herolist.add(new Hero(hero_id, cards, name, curse));
            }
            cursor.close();
        }

        // Load scenarios
        ArrayList<Quest.QuestFactory> questFactories = new ArrayList<>();
        cursor = db.rawQuery("SELECT * " +
                            "FROM quest_play " +
                            "WHERE campaign_id = ?"
                            , new String[]{String.valueOf(id)});
        while(cursor.moveToNext()) {
            Quest.QuestFactory factory = new Quest.QuestFactory();
            factory.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            factory.setBaseId(cursor.getInt(cursor.getColumnIndexOrThrow("quest_id")));

            questFactories.add(factory);
        }
        cursor.close();

        for(Quest.QuestFactory factory : questFactories) {
            // Load base quest
            cursor = db.rawQuery("SELECT * " +
                                "FROM quest_static " +
                                "WHERE _id = ?"
                                , new String[]{String.valueOf(factory.getBaseId())});
            cursor.moveToFirst();
            factory.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
            factory.setDifficulty(cursor.getString(cursor.getColumnIndexOrThrow("difficulty")));
            factory.setCircle(cursor.getInt(cursor.getColumnIndexOrThrow("circle")));
            factory.setHasReward(cursor.getInt(cursor.getColumnIndexOrThrow("has_reward")) > 0);
            factory.setHasTitle(cursor.getInt(cursor.getColumnIndexOrThrow("has_title")) > 0);
            int givesIcon = cursor.getInt(cursor.getColumnIndexOrThrow("gives_icon"));
            cursor.close();

            // load quest attributes
            cursor = db.rawQuery("SELECT * " +
                                "FROM quest_attribute " +
                                "WHERE quest_id = ?"
                                , new String[]{String.valueOf(factory.getId())});
            while(cursor.moveToNext()) {
                int playerId = cursor.getInt(cursor.getColumnIndexOrThrow("player_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                switch (name) {
                    case "least deaths":
                        factory.getLeastDeaths().add(result.getPlayer(playerId));
                        break;
                    case "most coins":
                        factory.getMostCoins().add(result.getPlayer(playerId));
                        break;
                    case "won reward":
                        factory.getWonReward().add(result.getPlayer(playerId));
                        break;
                }
            }
            cursor.close();

            // load icons
            ArrayList<Integer> iconIds = new ArrayList<>();
            iconIds.add(givesIcon);

            cursor = db.rawQuery("SELECT * " +
                                "FROM uses_icon " +
                                "WHERE quest_id = ?"
                                , new String[]{String.valueOf(factory.getBaseId())});

            while(cursor.moveToNext()) {
                iconIds.add(cursor.getInt(cursor.getColumnIndexOrThrow("icon_id")));
            }
            cursor.close();

            // the actual icons
            for(int i = 0; i < iconIds.size(); i++) {
                cursor = db.rawQuery("SELECT * " +
                        "FROM icon " +
                        "WHERE _id = ?"
                        , new String[]{String.valueOf(iconIds.get(i))});
                cursor.moveToFirst();
                int iconId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String img = cursor.getString(cursor.getColumnIndexOrThrow("img_file"));

                cursor.close();
                if(i == 0) {
                    factory.setGivesIcon(new Icon(iconId, name, img));
                } else {
                    factory.getUsesIcon().add(new Icon(iconId, name, img));
                }
            }

            // put in quests
            quests.add(factory.generate());
        }

        return result;
    }

    // SETTER METHODS

    public void saveCampaign(Campaign campaign) {
        ContentValues values = new ContentValues();
        values.put("completed", campaign.isCompleted());

        if(campaign.getId() >= 0) { // already saved, is an update
            db.update("campaign_play", values, "_id = ?", new String[]{String.valueOf(campaign.getId())});
        } else {
            int newId = (int)db.insert("campaign_play", null, values);
            campaign.setId(newId);
        }
    }

    public void saveQuest(Quest quest, int campaign_id) {
        ContentValues values = new ContentValues();
        values.put("quest_id", quest.getBaseId());
        values.put("campaign_id", campaign_id);

        if(quest.getId() >= 0) { // already saved, is an update
            db.update("quest_play", values, "_id = ?", new String[]{String.valueOf(quest.getId())});
        } else {
            int newId = (int)db.insert("quest_play", null, values);
            quest.setId(newId);
        }

        // Save auxilary tables
        saveQuestAttribute(quest.getId(), "least deaths", quest.getLeastDeaths());
        saveQuestAttribute(quest.getId(), "most coins", quest.getMostCoins());
        saveQuestAttribute(quest.getId(), "won reward", quest.getWonReward());
    }

    public void saveQuestAttribute(int quest_id, String attr_name, Set<Player> players) {
        db.delete("quest_attribute", "quest_id = ? AND name = ?", new String[]{String.valueOf(quest_id), attr_name});
        for(Player p : players) {
            ContentValues values = new ContentValues();
            values.put("quest_id", quest_id);
            values.put("name", attr_name);
            values.put("player_id", p.getId());

            db.insert("quest_attribute", null, values);
        }
    }

    public void savePlayer(Player player, int campaign_id) {
        ContentValues values = new ContentValues();
        values.put("campaign_id", campaign_id);
        values.put("name", player.getName());
        values.put("color", player.getColor());
        values.put("saved_gold", player.hasSavedGold());

        if(player.getId() >= 0) { // already saved, is an update
            db.update("player", values, "_id = ?", new String[]{String.valueOf(player.getId())});
        } else {
            int newId = (int)db.insert("player", null, values);
            player.setId(newId);
        }
    }

    public void saveHero(Hero hero, int player_id) {
        ContentValues values = new ContentValues();
        values.put("player_id", player_id);
        values.put("name", hero.getName());
        values.put("curse", hero.getCurse());
        for(int i = 0; i < hero.getCards().length && i < 4; i++) {
            values.put("card"+(i+1), hero.getCards()[i]);
        }

        if(hero.getId() >= 0) { // already saved, is an update
            db.update("hero_play", values, "_id = ?", new String[]{String.valueOf(hero.getId())});
        } else {
            int newId = (int)db.insert("hero_play", null, values);
            hero.setId(newId);
        }
    }

    // PRIVATE HELPER METHODS

    private int getIntColumn(String table, String id, String col_name) {
        Cursor cursor = db.query(table, new String[]{col_name}, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        int result = cursor.getInt(cursor.getColumnIndexOrThrow(col_name));
        cursor.close();
        return result;
    }
}
