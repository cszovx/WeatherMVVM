package com.hryt.weathermvvm.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherBean
 * version: 1.0
 */
public class WeatherBean {
    @SerializedName("updateTime")
    public String updateTime;
    @SerializedName("daily")
    public ArrayList<Daily> daily;


}
