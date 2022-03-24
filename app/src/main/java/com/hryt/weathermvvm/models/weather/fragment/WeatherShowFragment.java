package com.hryt.weathermvvm.models.weather.fragment;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;

import com.bumptech.glide.Glide;
import com.hryt.weathermvvm.MainActivity;
import com.hryt.weathermvvm.R;
import com.hryt.weathermvvm.bean.Daily;
import com.hryt.weathermvvm.bean.WeatherBean;
import com.hryt.weathermvvm.constants.WeatherConstants;
import com.hryt.weathermvvm.databinding.FragmentWeathershowBinding;
import com.hryt.weathermvvm.manager.WeatherAppManager;
import com.hryt.weathermvvm.models.base.BaseFragment;
import com.hryt.weathermvvm.models.china.fragment.ChooseAreaFragment;
import com.hryt.weathermvvm.models.weather.viewmodel.WeatherShowViewModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherShowFragment
 * version: 1.0
 */
public class WeatherShowFragment extends BaseFragment<FragmentWeathershowBinding, WeatherShowViewModel> {

    private MainActivity mainActivity;
    private ChooseAreaFragment fragment;
    Map<String, String> map = new HashMap<>();
    public WeatherShowFragment (MainActivity mainActivity){
        Observable.just(true)
                .map(is -> {
                    SharedPreferences.Editor editor = mainActivity.getSharedPreferences("data", 0).edit();
                    editor.putString("weatherId", WeatherAppManager.getInstance().getWeatherStatus().getWeatherId());
                    editor.putString("name", WeatherAppManager.getInstance().getWeatherStatus().getName());
                    editor.putInt("cityId", WeatherAppManager.getInstance().getWeatherStatus().getCityId());
                    editor.apply();
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

        map.put("location", WeatherAppManager.getInstance().getWeatherStatus().getWeatherId());
        map.put("key", WeatherConstants.KEY);
        this.mainActivity = mainActivity;
        if (this.mainActivity == null) {
            this.mainActivity = new MainActivity();
        }
        fragment = new ChooseAreaFragment(mainActivity);
    }

    @Override
    protected void init() {
        updateBackgroundPic();
        updateWeather();
        binding.navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.updateFragment(R.id.fragment_choose, fragment);
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        binding.swipeRefresh.setOnRefreshListener(() -> {
            binding.swipeRefresh.setRefreshing(true);
            updateBackgroundPic();
            Observable
                    .just(true)
                    .map(isRefresh -> {
                        updateWeather();
                        return false;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            isRefresh -> binding.swipeRefresh.setRefreshing(false),
                            error -> Log.e("", error + "")
                    );

        });
    }

    private void updateWeather() {
        WeatherAppManager.getInstance().getWeatherHttpManager()
                .getWeatherApi().getWeatherInfo(map).enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                Observable
                        .just(response.body().daily)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                dailies -> {
                                    LinearLayout forecastLayout = binding.weatherForecast.forecastLayout;
                                    forecastLayout.removeAllViews();
                                    for (Daily forecast : dailies) {
                                        View view = LayoutInflater.from(WeatherAppManager.getInstance()
                                                .getContext()).inflate(R.layout.forecast_item, forecastLayout, false);
                                        TextView dateText = (TextView) view.findViewById(R.id.date_text);
                                        ImageView infoText = (ImageView) view.findViewById(R.id.info_text);
                                        TextView maxText = (TextView) view.findViewById(R.id.max_text);
                                        TextView minText = (TextView) view.findViewById(R.id.min_text);
                                        dateText.setText(forecast.fxDate);
                                        infoText.setBackgroundResource(getRes("icon_" + forecast.iconDay));
                                        maxText.setText(forecast.tempMin + "℃");
                                        minText.setText(forecast.tempMax + "℃");
                                        forecastLayout.addView(view);
                                    }
                                    binding.titleCity.setText(WeatherAppManager.getInstance().getWeatherStatus().getName());
                                }, error -> Log.e("error: ", "" + error)
                        );
            }
            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                return;
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weathershow;
    }

    public int getRes(String name) {
        ApplicationInfo appInfo = mainActivity.getApplicationInfo();
        int resID = mainActivity.getResources().getIdentifier(name, "drawable",appInfo.packageName);
        return resID;
    }

    private void updateBackgroundPic(){
        Glide.with(this).load("https://scpic.chinaz.net/files/pic/pic7/xpic348.jpg").into(binding.bingPicImg);

    }
}
