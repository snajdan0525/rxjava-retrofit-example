package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Author :    snalopainen
 * Date :      10/2/2014
 */
@Parcel
public class Coord
{
    @SerializedName("lon")
    private Double longitude;

    @SerializedName("lat")
    private Double latitude;

    public Double getLongitude()
    {
        return longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }
}
