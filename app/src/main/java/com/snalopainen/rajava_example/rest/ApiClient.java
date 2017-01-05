package com.snalopainen.rajava_example.rest;

import com.snalopainen.rajava_example.rest.service.OpenWeatherMapService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by snajdan on 2017/1/5.
 */

public class ApiClient {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private OpenWeatherMapService apiService;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(OpenWeatherMapService.class);
    }

    public OpenWeatherMapService getApiService() {
        return apiService;
    }

}
