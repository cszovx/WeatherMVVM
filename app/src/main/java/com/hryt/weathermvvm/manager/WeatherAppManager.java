package com.hryt.weathermvvm.manager;

import android.content.Context;

import com.hryt.weathermvvm.database.WeatherDao;
import com.hryt.weathermvvm.database.WeatherDatabase;
import com.hryt.weathermvvm.net.AreaHttpManager;
import com.hryt.weathermvvm.net.WeatherHttpManager;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is WeatherAppManager
 * version: 1.0
 */
public class WeatherAppManager {
    private static final WeatherAppManager INSTANCE = new WeatherAppManager();
    private WeatherHttpManager weatherHttpManager;
    private AreaHttpManager areaHttpManager;
    private WeatherDao mWeatherDao;
    private Context mContext;
    private WeatherStatus mWeatherStatus;

    public static WeatherAppManager getInstance() {
        return INSTANCE;
    }

    private WeatherAppManager() {
    }

    public void init(Context context) {
        mContext = context;
        weatherHttpManager = WeatherHttpManager.getInstance();
        weatherHttpManager.init();
        areaHttpManager = AreaHttpManager.getInstance();
        areaHttpManager.init();
        mWeatherDao = WeatherDatabase.getDatabaseInstance(mContext).getWeatherDao();
        mWeatherStatus = new WeatherStatus();
    }

    public WeatherHttpManager getWeatherHttpManager() {
        return weatherHttpManager;
    }

    public AreaHttpManager getAreaHttpManager() {
        return areaHttpManager;
    }

    public WeatherDao getWeatherDao() {
        return mWeatherDao;
    }

    public WeatherStatus getWeatherStatus() {
        return mWeatherStatus;
    }

    public Context getContext() {
        return mContext;
    }
}
