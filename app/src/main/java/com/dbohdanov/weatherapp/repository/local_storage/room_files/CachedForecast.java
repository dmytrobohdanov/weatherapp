package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

/**
 *
 */
@Entity
public class CachedForecast {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "weather_forecast")
    @TypeConverters({DataWeatherConverters.class})
    private DataWeatherForecast dataWeatherForecast;

    public CachedForecast() {
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }

    public DataWeatherForecast getDataWeatherForecast() {
        return dataWeatherForecast;
    }

    public void setDataWeatherForecast(DataWeatherForecast dataWeatherForecast) {
        this.dataWeatherForecast = dataWeatherForecast;
    }
}
