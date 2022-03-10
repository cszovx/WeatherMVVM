package com.hryt.weathermvvm.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is Province
 * version: 1.0
 */
@Entity(tableName = "province")
public class Province {
    //PrimaryKey主键，autoGenerate自增长
    @PrimaryKey(autoGenerate = true)
    //ColumnInfo用于指定该字段存储在表中的名字，并指定类型
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "provinceName")
    private String provinceName;
    @ColumnInfo(name = "provinceCode")
    private int provinceCode;

    public Province(){}

    @Ignore
    public Province(String provinceName, int provinceCode){
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
