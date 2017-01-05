package com.snalopainen.rajava_example.rest.service;

import com.snalopainen.rajava_example.rest.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by snajdan on 2017/1/5.
 */

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather")
    public Observable<WeatherResponse> getWeather(@Query("q") String strCity, @Query("appid") String appid);


}
