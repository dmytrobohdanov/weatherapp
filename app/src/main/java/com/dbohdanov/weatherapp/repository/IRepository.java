package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.google.android.gms.location.places.Place;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 */
public interface IRepository {
    Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, double lat, double lon);

    Flowable<List<PlaceData>> getListOfResentCities();

    void addPlaceToRecent(Place place);
}
