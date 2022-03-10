package com.hryt.weathermvvm.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hryt.weathermvvm.database.bean.City;
import com.hryt.weathermvvm.database.bean.County;
import com.hryt.weathermvvm.database.bean.Province;
import java.util.List;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherDao
 * version: 1.0
 */
@Dao
public interface WeatherDao {
    //插入数据
    @Insert(entity = City.class)
    void insertCity(List<City> cityList);

    @Insert(entity = County.class)
    void insertCounty(List<County> countyList);

    @Insert(entity = Province.class)
    void insertProvince(List<Province> provinceList);

    //根据条件查询唯一的一个
    @Query("SELECt * FROM city WHERE id=:id")
    City queryCityById(int id);

    @Query("SELECt * FROM county WHERE id=:id")
    County queryCountyById(int id);

    @Query("SELECt * FROM province WHERE id=:id")
    Province queryProvinceById(int id);

    //根据条件查询唯一的一个
    @Query("SELECt * FROM city WHERE provinceId=:provinceId")
    List<City> queryCitiesById(int provinceId);

    @Query("SELECt * FROM county WHERE cityId=:cityId")
    List<County> queryCountiesById(int cityId);

    @Query("SELECt * FROM province WHERE id=:id")
    List<Province> queryProvincesById(int id);


    @Query("SELECt * FROM city")
    List<City> queryCities();

    @Query("SELECt * FROM county")
    List<County> queryCounties();

    @Query("SELECt * FROM province")
    List<Province> queryProvinces();

}
