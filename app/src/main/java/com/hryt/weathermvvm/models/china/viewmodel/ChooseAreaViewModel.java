package com.hryt.weathermvvm.models.china.viewmodel;

import android.view.View;
import android.widget.ArrayAdapter;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.hryt.weathermvvm.manager.WeatherAppManager;
import com.hryt.weathermvvm.models.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is ChinaViewModel
 * version: 1.0
 */
public class ChooseAreaViewModel extends BaseViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<Integer> visibilityBtn = new MutableLiveData<>(View.INVISIBLE);
    public MutableLiveData<ArrayAdapter<String>> adapter = new MutableLiveData<>(new ArrayAdapter<>(WeatherAppManager.getInstance().getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>()));
}
