package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;



public class Coord
{
    private Double longitude;

    private Double latitude;

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }
}
