package com.hryt.weathermvvm.net;

import com.hryt.weathermvvm.bean.WeatherBean;
import com.hryt.weathermvvm.constants.WeatherConstants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherApi
 * version: 1.0
 */
public interface WeatherApi {
    @GET("v7/weather/7d")
    Call<WeatherBean> getWeatherInfo(@QueryMap Map<String, String> location);
}
