package com.snalopainen.rajava_example;

/**
 * Created by snajdan on 2017/1/1.
 */

public class Students {
    public String[] getScores() {
        return scores;
    }

    public void setScores(String[] scores) {
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Students(String name, String[] scores) {
        this.name = name;
        this.scores = scores;
    }

    private String name;
    private String[] scores;
}
