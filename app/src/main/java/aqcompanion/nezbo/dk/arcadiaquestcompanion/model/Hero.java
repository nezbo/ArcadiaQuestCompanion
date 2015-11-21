package aqcompanion.nezbo.dk.arcadiaquestcompanion.model;

import java.util.List;

/**
 * Created by Emil on 15-11-2015.
 */
public class Hero {

    private int id;
    private String name;
    private final String[] cards;
    private String curse;

    public Hero(int id, String[] cards, String name, String curse) {
        this.id = id;
        this.cards = cards;
        this.name = name;
        this.curse = curse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getCards() {
        return cards;
    }

    public String getCurse() {
        return curse;
    }

    public String getName() {
        return name;
    }

    // SETTERS

    public void setCurse(String curse) {
        this.curse = curse;
    }

    public void setName(String name) {
        this.name = name;
    }
}
