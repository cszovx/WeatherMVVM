package com.hryt.weathermvvm.net;

import com.hryt.weathermvvm.bean.Area;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is AreaApi
 * version: 1.0
 */
public interface AreaApi {
    @GET("china")
    Call<ArrayList<Area>> getProvince();

    @GET("china/{province}")
    Call<ArrayList<Area>> getCity(@Path("province") int province);

    @GET("china/{province}/{city}")
    Call<ArrayList<Area>> getCountry(@Path("province") int province, @Path("city") int city);

}
