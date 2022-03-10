package com.hryt.weathermvvm.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Query;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is City
 * version: 1.0
 */
@Entity(tableName = "city")
public class City {
    //PrimaryKey主键，autoGenerate自增长
    @PrimaryKey(autoGenerate = true)
    //ColumnInfo用于指定该字段存储在表中的名字，并指定类型
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    private int id;
    @ColumnInfo(name = "cityName")
    private String cityName;
    @ColumnInfo(name = "cityCode")
    private int cityCode;
    @ColumnInfo(name = "provinceId")
    private int provinceId;

    public City(){}

    @Ignore
    public City(String cityName, int cityCode, int provinceId){
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.provinceId = provinceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
