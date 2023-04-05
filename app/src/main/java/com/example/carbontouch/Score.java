package com.example.carbontouch;

import java.sql.Date;

public class Score {

    private int id;
    private int score;
    private String date;

    public Score(int id, int score, String date) {
        this.id = id;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
