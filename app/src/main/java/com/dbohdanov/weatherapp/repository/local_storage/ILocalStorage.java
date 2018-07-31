package com.dbohdanov.weatherapp.repository.local_storage;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 */
public interface ILocalStorage {

    Flowable<List<PlaceData>> getListOfResentCities();

    void deleteCityFromResent(PlaceData placeData);

    void addCityToResent(PlaceData placeData);

    void saveForecast(DataWeatherForecast dataWeatherForecast);

    Single<DataWeatherForecast> getSavedWeatherForecast(String cityName);

    void cleanOutofdateData();
}
