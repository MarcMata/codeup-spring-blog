package com.codeup.codeupspringblog.models;

public class Dice {
    private int side;

    public Dice() {
    }

    public Dice(int sides) {
        this.side = sides;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int sides) {
        this.side = sides;
    }
}
