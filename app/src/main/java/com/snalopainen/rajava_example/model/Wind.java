package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Author :    Chutaux Robin
 * Date :      10/2/2014
 */
@Parcel
public class Wind
{
    @SerializedName("speed")
    private Double speed;

    public Double getSpeed()
    {
        return speed;
    }
}
