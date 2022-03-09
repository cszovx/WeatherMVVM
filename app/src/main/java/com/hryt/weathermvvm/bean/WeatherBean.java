package com.hryt.weathermvvm.bean;

import com.google.gson.annotations.SerializedName;

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

    public class daily {
        @SerializedName("fxDate")
        public String fxDate;
        @SerializedName("min")
        public String min;
        @SerializedName("tempMax")
        public String tempMax;
        @SerializedName("tempMin")
        public String tempMin;
        @SerializedName("iconDay")
        public String iconDay;
        @SerializedName("textDay")
        public String textDay;
        @SerializedName("uvIndex")
        public String uvIndex;
        @SerializedName("humidity")
        public String humidity;


    }
}
