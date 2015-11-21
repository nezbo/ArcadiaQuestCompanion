package aqcompanion.nezbo.dk.arcadiaquestcompanion.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 15-11-2015.
 */
public class Campaign {

    private int id;
    private final int base_id;
    private final String title;
    private final List<Quest> quests;
    private final List<Player> players;
    private boolean completed;

    public Campaign(int base_id, int id, String title, List<Player> players, List<Quest> quests, boolean completed) {
        this.base_id = base_id;
        this.id = id;
        if(players != null) {
            this.players = players;
        }else {
            this.players = new ArrayList<>();
        }
        this.title = title;
        if(quests != null) {
            this.quests = quests;
        } else {
            this.quests = new ArrayList<>();
        }
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseId() {
        return base_id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    // SETTERS

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // FUNKY METHODS

    public Player getPlayer(int id) {
        for(Player p : getPlayers()) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }
}
