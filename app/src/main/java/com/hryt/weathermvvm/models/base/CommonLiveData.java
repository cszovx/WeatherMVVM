package com.hryt.weathermvvm.models.base;

import androidx.lifecycle.MutableLiveData;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/11
 * desc   : This is CommonLiveData
 * version: 1.0
 */
public class CommonLiveData <T> extends MutableLiveData<T> {

    private boolean mIsInitFinished;
    private boolean mIsValueChanged;

    public CommonLiveData() {
        mIsInitFinished = true;
    }

    public CommonLiveData(T value) {
        setValue(value);
        mIsInitFinished = true;
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        mIsValueChanged = true;
    }

    public boolean isInitFinished() {
        return mIsInitFinished;
    }

    public boolean isValueChanged() {
        return mIsValueChanged;
    }

    public void setValueChanged(boolean valueChanged) {
        mIsValueChanged = valueChanged;
    }
}
