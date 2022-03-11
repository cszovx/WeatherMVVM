package com.hryt.weathermvvm.models.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/08
 * desc   : This is BaseFragment
 * version: 1.0
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected VM viewModel;
    protected B binding;
    private ViewModelProvider mFragmentProvider;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        injectViewModel();
        init();
        return binding.getRoot();
    }

    protected void injectViewModel() {
        Class<VM> viewModelClass = getViewModelClass();
        viewModel = getViewModel(viewModelClass);
        getLifecycle().addObserver(viewModel);
    }

    public <FVM extends BaseViewModel> FVM getViewModel(Class<FVM> viewModelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory());
        }
        return mFragmentProvider.get(viewModelClass);
    }


    private Class<VM> getViewModelClass() {
        Class targetClass = this.getClass();
        while (!targetClass.getClass().equals(BaseFragment.class)) {
            ParameterizedType paramType = (ParameterizedType) targetClass.getGenericSuperclass();
            Type[] typeArguments = paramType.getActualTypeArguments();
            for (Type type : typeArguments) {
                if (type instanceof Class && ViewModel.class.isAssignableFrom((Class<?>) type)) {
                    return (Class<VM>) type;
                }
            }
            targetClass = targetClass.getSuperclass();
        }
        return null;
    }

    protected abstract void init();

    @LayoutRes
    protected abstract int getLayoutId();
}
