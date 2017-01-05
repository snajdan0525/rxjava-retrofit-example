package com.snalopainen.rajava_example.rest.model;

import com.google.gson.annotations.SerializedName;
import com.snalopainen.rajava_example.model.Coord;
import com.snalopainen.rajava_example.model.MainInfos;
import com.snalopainen.rajava_example.model.Sys;
import com.snalopainen.rajava_example.model.Weather;
import com.snalopainen.rajava_example.model.Wind;

import java.util.ArrayList;

/**
 * Created by snajdan on 2017/1/5.
 */

public class WeatherResponse {
    @SerializedName("coord")
    private Coord coord;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("main")
    private MainInfos mainInfos;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("name")
    private String strCityName;

    public Coord getCoord()
    {
        return coord;
    }

    public Sys getSys()
    {
        return sys;
    }

    public ArrayList<Weather> getWeather()
    {
        return weather;
    }

    public MainInfos getMainInfos()
    {
        return mainInfos;
    }

    public Wind getWind()
    {
        return wind;
    }

    public String getStrCityName()
    {
        return strCityName;
    }
}
