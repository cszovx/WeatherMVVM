package com.hryt.weathermvvm.models.china.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.hryt.weathermvvm.MainActivity;
import com.hryt.weathermvvm.R;
import com.hryt.weathermvvm.bean.Area;
import com.hryt.weathermvvm.database.WeatherDao;
import com.hryt.weathermvvm.database.bean.City;
import com.hryt.weathermvvm.database.bean.County;
import com.hryt.weathermvvm.database.bean.Province;
import com.hryt.weathermvvm.databinding.FragmentChooseareaBinding;
import com.hryt.weathermvvm.manager.WeatherAppManager;
import com.hryt.weathermvvm.models.base.BaseFragment;
import com.hryt.weathermvvm.models.china.viewmodel.ChooseAreaViewModel;
import com.hryt.weathermvvm.net.AreaApi;

import java.util.ArrayList;
import java.util.List;

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
 * desc   : This is ChinaFragment
 * version: 1.0
 */
public class ChooseAreaFragment extends BaseFragment<FragmentChooseareaBinding, ChooseAreaViewModel> {

    private final WeatherDao mWeatherDao = WeatherAppManager.getInstance().getWeatherDao();

    private final AreaApi mAreaApi = WeatherAppManager.getInstance().getAreaHttpManager().getAreaApi();

    private static final String TAG = "ChooseAreaFragment";

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    private MainActivity mainActivity;

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;

    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * 当前选中的级别
     */
    private int currentLevel = LEVEL_PROVINCE;

    public ChooseAreaFragment(){}

    public ChooseAreaFragment(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void init() {
        binding.setViewModel(viewModel);
//        binding.setLifecycleOwner(this);
        binding.setOnClick(new ClickProxy());
        viewModel.name.setValue("中国");
        Log.d(TAG + "MAIN",Thread.currentThread().getId() + viewModel.name.getValue());
        currentLevel = 0;
        listView = binding.listView;
        viewModel.visibilityBtn.setValue(View.INVISIBLE);
        mAreaApi.getProvince().enqueue(new Callback<ArrayList<Area>>() {
            @Override
            public void onResponse(Call<ArrayList<Area>> call, Response<ArrayList<Area>> response) {
                Observable.just(response.body())
                        .map(areas -> {
                            List<Province> provinces = new ArrayList<>();
                            if(mWeatherDao.queryProvinces() != null && mWeatherDao.queryProvinces().size() != 0) {
                                return provinces;
                            }
                            for (Area area : areas) {
                                Province province = new Province(area.name, area.id);
                                provinces.add(province);
                            }
                            mWeatherDao.insertProvince(provinces);
                            return provinces;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                isUpdate -> {
                                    if (isUpdate.size() == 0 || isUpdate == null){
                                        return;
                                    }
                                    provinceList = isUpdate;
                                    dataList.clear();
                                    for (Province p : isUpdate) {
                                        dataList.add(p.getProvinceName());
                                    }
                                    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                                    viewModel.adapter.setValue(adapter);
                                    binding.listView.setVisibility(View.VISIBLE);
                                    binding.progressBar.setVisibility(View.INVISIBLE);
                                    listView.setOnItemClickListener((parent, view, position, id) -> {
                                        if (currentLevel == LEVEL_PROVINCE) {
                                            binding.listView.setVisibility(View.INVISIBLE);
                                            binding.progressBar.setVisibility(View.VISIBLE);
                                            selectedProvince = provinceList.get(position);
                                            updateCityDatabase(selectedProvince);
                                            currentLevel = LEVEL_CITY;
                                            viewModel.name.setValue(selectedProvince.getProvinceName());
                                            viewModel.visibilityBtn.setValue(View.VISIBLE);
                                            return;
                                        }
                                        if (currentLevel == LEVEL_CITY) {
                                            binding.listView.setVisibility(View.INVISIBLE);
                                            binding.progressBar.setVisibility(View.VISIBLE);
                                            selectedCity = cityList.get(position);
                                            updateCountryDatabase(selectedCity);
                                            currentLevel = LEVEL_COUNTY;
                                            viewModel.name.setValue(selectedCity.getCityName());
                                            return;
                                        }
                                        if (currentLevel == LEVEL_COUNTY) {
                                            County county = countyList.get(position);
                                            WeatherAppManager.getInstance().getWeatherStatus().setWeatherId(county.getWeatherId());
                                            WeatherAppManager.getInstance().getWeatherStatus().setName(county.getCountyName());
                                            WeatherAppManager.getInstance().getWeatherStatus().setCityId(county.getCityId());
                                            mainActivity.updateFragment(R.id.item_weather, mainActivity.weatherShowFragment);
                                            return;}
                                    });
                                },
                                error -> Log.e(TAG, error + "")
                        );
            }

            @Override
            public void onFailure(Call<ArrayList<Area>> call, Throwable t) {
                return;
            }
        });

        Observable.just(true)
                .map(is -> {
                    return mWeatherDao.queryProvinces();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(provinces -> {
                    if (provinces != null && provinces.size() != 0) {
                        dataList.clear();
                        for (Province p : provinces) {
                            dataList.add(p.getProvinceName());
                        }
                        provinceList = provinces;
                        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                        viewModel.adapter.setValue(adapter);
                        binding.listView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        listView.setOnItemClickListener((parent, view, position, id) -> {
                                if (currentLevel == LEVEL_PROVINCE) {
                                    binding.listView.setVisibility(View.INVISIBLE);
                                    binding.progressBar.setVisibility(View.VISIBLE);
                                    selectedProvince = provinceList.get(position);
                                    updateCityDatabase(selectedProvince);
                                    currentLevel = LEVEL_CITY;
                                    viewModel.name.setValue(selectedProvince.getProvinceName());
                                    viewModel.visibilityBtn.setValue(View.VISIBLE);
                                    return;
                                }
                                if (currentLevel == LEVEL_CITY) {
                                    binding.listView.setVisibility(View.INVISIBLE);
                                    binding.progressBar.setVisibility(View.VISIBLE);
                                    selectedCity = cityList.get(position);
                                    updateCountryDatabase(selectedCity);
                                    currentLevel = LEVEL_COUNTY;
                                    viewModel.name.setValue(selectedCity.getCityName());
                                    return;
                                }
                                if (currentLevel == LEVEL_COUNTY) {
                                    County county = countyList.get(position);
                                    WeatherAppManager.getInstance().getWeatherStatus().setWeatherId(county.getWeatherId());
                                    WeatherAppManager.getInstance().getWeatherStatus().setName(county.getCountyName());
                                    WeatherAppManager.getInstance().getWeatherStatus().setCityId(county.getCityId());
                                    mainActivity.updateFragment();
                                    return;}
                            });
                    }
                }, error -> Log.e(TAG, error + ""));
    }

    private void updateCityDatabase(Province province){
        mAreaApi.getCity(province.getProvinceCode()).enqueue(new Callback<ArrayList<Area>>() {
            @Override
            public void onResponse(Call<ArrayList<Area>> call, Response<ArrayList<Area>> response) {
                Observable.just(response.body())
                        .map(areas -> {
                            List<City> cities = new ArrayList<>();
                            for (Area area : areas) {
                                City city = new City(area.name, area.id, province.getProvinceCode());
                                cities.add(city);
                            }
                            if (mWeatherDao.queryCitiesById(province.getProvinceCode()).size() == 0 || mWeatherDao.queryCitiesById(province.getProvinceCode()) == null) {
                                mWeatherDao.insertCity(cities);
                            }
                            return cities;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cities -> {
                            if (cities != null || cities.size() != 0){
                                cityList = cities;
                                dataList.clear();
                                for (City c : cityList) {
                                    dataList.add(c.getCityName());
                                }
                                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                                viewModel.adapter.setValue(adapter);
                                binding.listView.setVisibility(View.VISIBLE);
                                binding.progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }

            @Override
            public void onFailure(Call<ArrayList<Area>> call, Throwable t) {

            }
        });
    }

    private void updateCountryDatabase(City city){
        mAreaApi.getCountry(city.getProvinceId(), city.getCityCode()).enqueue(new Callback<ArrayList<Area>>() {
            @Override
            public void onResponse(Call<ArrayList<Area>> call, Response<ArrayList<Area>> response) {
                Observable.just(response.body())
                        .map(areas -> {
                            List<County> counties = new ArrayList<>();
                            for (Area area : areas) {
                                County county = new County(area.name, area.weatherId, city.getCityCode());
                                counties.add(county);
                            }
                            if (mWeatherDao.queryCountiesById(city.getCityCode()).size() == 0 || mWeatherDao.queryCountiesById(city.getCityCode()) == null) {
                                mWeatherDao.insertCounty(counties);
                            }
                            return counties;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(counties -> {
                            if (counties != null || counties.size() != 0){
                                countyList = counties;
                                dataList.clear();
                                for (County c : countyList) {
                                    dataList.add(c.getCountyName());
                                }
                                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                                viewModel.adapter.setValue(adapter);
                                binding.listView.setVisibility(View.VISIBLE);
                                binding.progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }

            @Override
            public void onFailure(Call<ArrayList<Area>> call, Throwable t) {

            }
        });
    }

    private void updateCountryWeather(County county){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choosearea;
    }

    public class ClickProxy{
        public void search() {
            if (currentLevel == 1) {
                currentLevel = 0;
                dataList.clear();
                for (Province p : provinceList) {
                    dataList.add(p.getProvinceName());
                }
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                viewModel.adapter.setValue(adapter);
                binding.listView.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.INVISIBLE);
                viewModel.visibilityBtn.setValue(View.INVISIBLE);
                viewModel.name.setValue("中国");
                Log.d(TAG + "BACK",Thread.currentThread().getId() + viewModel.name.getValue());
            }
            if (currentLevel == 2) {
                currentLevel = 1;
                dataList.clear();
                for (City c : cityList) {
                    dataList.add(c.getCityName());
                }
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
                viewModel.adapter.setValue(adapter);
                binding.listView.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.INVISIBLE);
                viewModel.name.setValue(selectedProvince.getProvinceName());
                Log.d(TAG + "BACK",Thread.currentThread().getId() + viewModel.name.getValue());
            }
        }
    }

}
