package com.hryt.weathermvvm.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is Area
 * version: 1.0
 */
public class Area {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("weather_id")
    public String weatherId;
}
