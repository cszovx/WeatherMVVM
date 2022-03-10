package com.hryt.weathermvvm;

import android.app.Application;

import com.hryt.weathermvvm.manager.WeatherAppManager;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is MVVMApplication
 * version: 1.0
 */
public class MVVMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WeatherAppManager.getInstance().init(this);
    }
}
