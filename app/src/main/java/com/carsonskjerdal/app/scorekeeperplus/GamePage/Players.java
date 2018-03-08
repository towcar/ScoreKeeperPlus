package com.carsonskjerdal.app.scorekeeperplus.GamePage;

/**
 * Created by Carson on 2018-02-01.
 * <p>
 * Feel free to use code just give credit please :)
 */

class Players {

    private String name;
    private int score;
    private  boolean selected;

    public Players(String name, int score, boolean selected){

        this.name = name;
        this.score = score;
        this.selected = selected;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean getSelectState() {
        return selected;
    }
    public void setSelectState(boolean selected) {
        this.selected = selected;
    }
}
