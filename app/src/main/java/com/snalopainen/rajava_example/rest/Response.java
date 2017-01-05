package com.snalopainen.rajava_example.rest;

import com.snalopainen.rajava_example.model.Coord;
import com.snalopainen.rajava_example.model.MainInfos;
import com.snalopainen.rajava_example.model.Sys;
import com.snalopainen.rajava_example.model.Weather;
import com.snalopainen.rajava_example.model.Wind;

import java.util.ArrayList;

/**
 * Created by snajdan on 2017/1/5.
 */

public class Response {
    private Coord coord;

    private Sys sys;

    private ArrayList<Weather> weather;

    private MainInfos mainInfos;

    private Wind wind;

    private String strCityName;

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public MainInfos getMainInfos() {
        return mainInfos;
    }

    public Wind getWind() {
        return wind;
    }

    public String getStrCityName() {
        return strCityName;
    }
}
