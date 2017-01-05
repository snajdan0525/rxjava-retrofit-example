package com.snalopainen.rajava_example;

import com.snalopainen.rajava_example.rest.Response;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by snajdan on 2017/1/5.
 */

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather")
    public void getWeather(@Query("q") String strCity, @Query("appid") String appid, Callback<Response> callback);


}
