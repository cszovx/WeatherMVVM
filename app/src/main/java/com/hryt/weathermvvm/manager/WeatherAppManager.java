package com.hryt.weathermvvm.manager;

import android.content.Context;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is WeatherAppManager
 * version: 1.0
 */
public class WeatherAppManager {
    private static final WeatherAppManager INSTANCE = new WeatherAppManager();
    private Context mContext;

    public static WeatherAppManager getInstance() {
        return INSTANCE;
    }

    private WeatherAppManager() {
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
