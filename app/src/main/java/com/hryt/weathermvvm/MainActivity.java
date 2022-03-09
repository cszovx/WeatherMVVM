package com.hryt.weathermvvm;

import android.app.Activity;

import com.hryt.weathermvvm.databinding.ActivityMainBinding;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is MainActivity
 * version: 1.0
 */
public class MainActivity extends BaseActivity<ActivityMainBinding,MainActivityViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        binding.setViewModel(viewModel);
    }

    @Override
    protected Activity setActivity() {
        return this;
    }
}
