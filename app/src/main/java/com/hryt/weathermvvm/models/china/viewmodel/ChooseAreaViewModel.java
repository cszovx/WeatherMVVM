package com.hryt.weathermvvm.models.china.viewmodel;

import com.hryt.weathermvvm.models.base.BaseViewModel;
import com.hryt.weathermvvm.models.base.CommonLiveData;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is ChinaViewModel
 * version: 1.0
 */
public class ChooseAreaViewModel extends BaseViewModel {
    public CommonLiveData<String> name = new CommonLiveData<>("");
}
