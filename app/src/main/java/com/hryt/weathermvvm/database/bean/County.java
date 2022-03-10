package com.hryt.weathermvvm.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is County
 * version: 1.0
 */
@Entity(tableName = "county")
public class County {
    //PrimaryKey主键，autoGenerate自增长
    @PrimaryKey(autoGenerate = true)
    //ColumnInfo用于指定该字段存储在表中的名字，并指定类型
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "countyName")
    private String countyName;
    @ColumnInfo(name = "weatherId")
    private String weatherId;
    @ColumnInfo(name = "cityId")
    private int cityId;

    public County(){}

    @Ignore
    public County(String countyName, String weatherId, int cityId){
        this.countyName = countyName;
        this.weatherId = weatherId;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
