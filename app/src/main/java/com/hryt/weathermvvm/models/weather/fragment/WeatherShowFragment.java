package com.hryt.weathermvvm.models.weather.fragment;

import com.hryt.weathermvvm.R;
import com.hryt.weathermvvm.databinding.FragmentWeathershowBinding;
import com.hryt.weathermvvm.models.base.BaseFragment;
import com.hryt.weathermvvm.models.weather.viewmodel.WeatherShowViewModel;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherShowFragment
 * version: 1.0
 */
public class WeatherShowFragment extends BaseFragment<FragmentWeathershowBinding, WeatherShowViewModel> {
    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weathershow;
    }
}
