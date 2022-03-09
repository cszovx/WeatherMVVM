package com.hryt.weathermvvm.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherHttpManager
 * version: 1.0
 */
public class WeatherHttpManager {
    private static final WeatherHttpManager INSTANCE = new WeatherHttpManager();
    private WeatherApi mWeatherApi;
    private Retrofit retrofit;

    public static WeatherHttpManager getInstance() {
        return INSTANCE;
    }

    private WeatherHttpManager() {
    }


    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://devapi.qweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherApi = retrofit.create(WeatherApi.class);
    }

    public WeatherApi getWeatherApi() {
        return mWeatherApi;
    }
}
