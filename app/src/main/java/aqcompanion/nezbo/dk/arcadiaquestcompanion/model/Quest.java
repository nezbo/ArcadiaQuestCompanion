package aqcompanion.nezbo.dk.arcadiaquestcompanion.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Emil on 15-11-2015.
 */
public class Quest {

    // Basic static stuff
    private final int base_id;
    private final String title;
    private final String difficulty;
    private final int circle;
    private final boolean has_reward;
    private final boolean has_title;
    private final Icon gives_icon;
    private final Set<Icon> uses_icon;

    // Can change
    private int id;
    private Player winner;
    private final Set<Player> least_deaths;
    private final Set<Player> most_coins;
    private final Set<Player> won_reward;
    private Player won_title;

    public Quest(int base_id, int id, int circle, String title, String difficulty, boolean has_reward, boolean has_title, Icon gives_icon, Set<Icon> uses_icon, Player winner, Set<Player> least_deaths, Set<Player> most_coins, Set<Player> won_reward, Player won_title) {
        this.base_id = base_id;
        this.id = id;
        this.circle = circle;
        this.title = title;
        this.difficulty = difficulty;
        this.has_reward = has_reward;
        this.has_title = has_title;
        this.gives_icon = gives_icon;
        this.uses_icon = uses_icon;
        this.winner = winner;
        this.least_deaths = least_deaths;
        this.most_coins = most_coins;
        this.won_reward = won_reward;
        this.won_title = won_title;
    }

    public int getBaseId() {
        return base_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCircle() {
        return circle;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Icon getGivesIcon() {
        return gives_icon;
    }

    public boolean hasReward() {
        return has_reward;
    }

    public boolean hasTitle() {
        return has_title;
    }

    public Set<Player> getLeastDeaths() {
        return least_deaths;
    }

    public Set<Player> getMostCoins() {
        return most_coins;
    }

    public String getTitle() {
        return title;
    }

    public Set<Icon> getUsesIcon() {
        return uses_icon;
    }

    public Player getWinner() {
        return winner;
    }

    public Set<Player> getWonReward() {
        return won_reward;
    }

    public Player getWonTitle() {
        return won_title;
    }

    // SETTERS

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setWon_title(Player won_title) {
        this.won_title = won_title;
    }

    // FACTORY

    public static class QuestFactory {
        // Basic static stuff
        private int base_id;
        private String title;
        private String difficulty;
        private int circle;
        private boolean has_reward;
        private boolean has_title;
        private Icon gives_icon;
        private final Set<Icon> uses_icon;

        // Can change
        private int id;
        private Player winner;
        private final Set<Player> least_deaths;
        private final Set<Player> most_coins;
        private final Set<Player> won_reward;
        private Player won_title;

        public QuestFactory() {
            this.uses_icon = new HashSet<>();
            this.least_deaths = new HashSet<>();
            this.most_coins = new HashSet<>();
            this.won_reward = new HashSet<>();
        }

        public Set<Player> getLeastDeaths() {
            return least_deaths;
        }

        public Set<Player> getMostCoins() {
            return most_coins;
        }

        public Set<Icon> getUsesIcon() {
            return uses_icon;
        }

        public Set<Player> getWonReward() {
            return won_reward;
        }

        public int getBaseId() {
            return base_id;
        }

        public void setBaseId(int base_id) {
            this.base_id = base_id;
        }

        public int getCircle() {
            return circle;
        }

        public void setCircle(int circle) {
            this.circle = circle;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public Icon getGivesIcon() {
            return gives_icon;
        }

        public void setGivesIcon(Icon gives_icon) {
            this.gives_icon = gives_icon;
        }

        public boolean hasReward() {
            return has_reward;
        }

        public void setHasReward(boolean has_reward) {
            this.has_reward = has_reward;
        }

        public boolean hasTitle() {
            return has_title;
        }

        public void setHasTitle(boolean has_title) {
            this.has_title = has_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Player getWinner() {
            return winner;
        }

        public void setWinner(Player winner) {
            this.winner = winner;
        }

        public Player getWonTitle() {
            return won_title;
        }

        public void setWonTitle(Player won_title) {
            this.won_title = won_title;
        }

        public Quest generate() {
            return new Quest(getBaseId(), getId(), getCircle(), getTitle(), getDifficulty(), hasReward(), hasTitle(), getGivesIcon(), getUsesIcon(), getWinner(), getLeastDeaths(), getMostCoins(), getWonReward(), getWonTitle());
        }
    }
}
