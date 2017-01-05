package com.snalopainen.rajava_example;

import android.app.Application;

import com.snalopainen.rajava_example.rest.ApiClient;

/**
 * Created by snajdan on 2017/1/5.
 */

public class ExampleApplication extends Application {
    private static ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new ApiClient();

    }

    public static ApiClient getApiClient() {
        return apiClient;
    }

}
