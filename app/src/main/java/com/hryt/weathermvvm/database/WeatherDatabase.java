package com.hryt.weathermvvm.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.hryt.weathermvvm.database.bean.City;
import com.hryt.weathermvvm.database.bean.County;
import com.hryt.weathermvvm.database.bean.Province;

/**
 * author : SizheChen
 * e-mail : Sizhe_Chen@human-horizons.com
 * time   : 2022/03/09
 * desc   : This is WeatherDatabase
 * version: 1.0
 */
@Database(entities = {City.class, County.class, Province.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "china_db";

    private static WeatherDatabase databaseInstance;

    public static synchronized WeatherDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherDatabase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract WeatherDao getWeatherDao();
}
