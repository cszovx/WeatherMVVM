package com.hryt.weathermvvm;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hryt.weathermvvm.database.bean.County;
import com.hryt.weathermvvm.databinding.ActivityMainBinding;
import com.hryt.weathermvvm.manager.WeatherAppManager;
import com.hryt.weathermvvm.models.china.fragment.ChooseAreaFragment;
import com.hryt.weathermvvm.models.weather.fragment.WeatherShowFragment;

/**
 * author : SizheChen
 * time   : 2022/03/08
 * desc   : This is MainActivity
 * version: 1.0
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {
    public WeatherShowFragment weatherShowFragment;
    public WeatherShowFragment weatherShowFragments;
    private boolean isChoose = true;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        binding.setViewModel(viewModel);
        SharedPreferences shared = getSharedPreferences("isFirst", MODE_PRIVATE);
        boolean isFirst = shared.getBoolean("isFirst", true);
        if(isFirst){
            //第一次进入跳转
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean("isFirst", false);
            SharedPreferences.Editor editors = getSharedPreferences("data", MODE_PRIVATE).edit();
            editors.putString("weatherId", "101010100");
            editors.putString("name", "北京");
            editors.putInt("cityId", 1);
            editor.apply();
            editors.apply();
        }
        SharedPreferences data = getSharedPreferences("data", 0);
        WeatherAppManager.getInstance().getWeatherStatus().setWeatherId(data.getString("weatherId", "101010100"));
        WeatherAppManager.getInstance().getWeatherStatus().setName(data.getString("name", "北京"));
        WeatherAppManager.getInstance().getWeatherStatus().setCityId(data.getInt("cityId", 1));
        weatherShowFragment = new WeatherShowFragment(this);
        weatherShowFragments = new WeatherShowFragment(this);
        updateFragment(binding.itemWeather.getId(), weatherShowFragment);
    }

    public void updateFragment(int id, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void updateFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isChoose) {
            weatherShowFragments = new WeatherShowFragment(this);
            fragmentTransaction.replace(binding.itemWeather.getId(),weatherShowFragments);
            isChoose = false;
        } else {
            weatherShowFragment = new WeatherShowFragment(this);
            fragmentTransaction.replace(binding.itemWeather.getId(),weatherShowFragment);
            isChoose = true;
        }
        fragmentTransaction.commit();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }
}
