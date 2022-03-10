package com.hryt.weathermvvm.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is AreaHttpManager
 * version: 1.0
 */
public class AreaHttpManager {
    private static final AreaHttpManager INSTANCE = new AreaHttpManager();
    private AreaApi mAreaApi;
    private Retrofit retrofit;

    public static AreaHttpManager getInstance() {
        return INSTANCE;
    }

    private AreaHttpManager() {
    }


    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://guolin.tech/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mAreaApi = retrofit.create(AreaApi.class);
    }

    public AreaApi getAreaApi() {
        return mAreaApi;
    }
}
