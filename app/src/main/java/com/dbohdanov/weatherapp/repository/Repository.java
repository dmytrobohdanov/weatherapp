package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.LocalStorage;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.repository.network.Network;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 */
public class Repository implements IRepository {

    private LocalStorage localStorage;

    public Repository() {
        localStorage = new LocalStorage();
    }

    @Override
    public Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, PlaceData place) {
        if (isNetworkAvailable) {
            return new Network().getForecastForFiveDays(place.getLatitude(), place.getLongitude())
                    .map(this::saveForecastToCash)
                    .map(dataWeatherForecast -> dataWeatherForecast.setIsOnlineData(true));
        } else {
            return localStorage.getSavedWeatherForecast(place.getName());
        }
    }

    @Override
    public Flowable<List<PlaceData>> getListOfResentCities() {
        return localStorage.getListOfResentCities();
    }

    private DataWeatherForecast saveForecastToCash(DataWeatherForecast dataWeatherForecast) {
        localStorage.saveForecast(dataWeatherForecast);
        return dataWeatherForecast;
    }

    @Override
    public void addPlaceToRecent(PlaceData place) {
        localStorage.addCityToResent(place);
    }

    @Override
    public void removeFromRecent(PlaceData place) {
        localStorage.deleteCityFromResent(place);
    }
}
