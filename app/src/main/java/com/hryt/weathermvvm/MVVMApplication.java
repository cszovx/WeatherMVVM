package com.hryt.weathermvvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hryt.weathermvvm.manager.WeatherAppManager;

import java.io.File;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is MVVMApplication
 * version: 1.0
 */
public class MVVMApplication extends Application {
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        WeatherAppManager.getInstance().init(this);
    }
}
