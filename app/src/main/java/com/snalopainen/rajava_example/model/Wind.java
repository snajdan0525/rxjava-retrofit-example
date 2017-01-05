package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;



public class Wind
{
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    private Double speed;

    public Double getSpeed()
    {
        return speed;
    }
}
