package aqcompanion.nezbo.dk.arcadiaquestcompanion.model;

import java.util.List;

/**
 * Created by Emil on 15-11-2015.
 */
public class Player {

    private int id;
    private String name;
    private String color;
    private boolean saved_gold;
    private final List<Hero> heroes;

    public Player(int id, String color, String name, boolean saved_gold, List<Hero> heroes) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.saved_gold = saved_gold;
        this.heroes = heroes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean hasSavedGold() {
        return saved_gold;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    // SETTERS

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSavedGold(boolean saved_gold) {
        this.saved_gold = saved_gold;
    }
}
