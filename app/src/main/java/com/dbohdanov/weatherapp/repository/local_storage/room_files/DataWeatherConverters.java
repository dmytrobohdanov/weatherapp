package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.TypeConverter;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.google.gson.Gson;

/**
 * converter to store dataweather object in DB
 */
public class DataWeatherConverters {
    @TypeConverter
    public static DataWeatherForecast fromString(String value) {
        return new Gson().fromJson(value, DataWeatherForecast.class);
    }

    @TypeConverter
    public static String fromArrayLisr(DataWeatherForecast dataWeatherForecast) {
        return new Gson().toJson(dataWeatherForecast);
    }
}
