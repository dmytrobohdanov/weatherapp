package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 */
public interface IRepository {

    Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, PlaceData place);

    Flowable<List<PlaceData>> getListOfResentCities();

    void addPlaceToRecent(PlaceData place);

    void removeFromRecent(PlaceData place);
}
