package com.hryt.weathermvvm;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is BaseActivity
 * version: 1.0
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    protected VM viewModel;
    protected B binding;
    private Activity activity = setActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(activity, getLayoutId());
        init();
    }

    protected abstract void init();

    protected abstract Activity setActivity();


    @LayoutRes
    protected abstract int getLayoutId();

}
