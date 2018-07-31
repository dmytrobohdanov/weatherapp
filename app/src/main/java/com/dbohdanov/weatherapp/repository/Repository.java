package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.LocalStorage;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.repository.network.Network;
import com.dbohdanov.weatherapp.utils.Utils;
import com.google.android.gms.location.places.Place;

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
    public Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, double lat, double lon) {
        if (isNetworkAvailable) {
            return new Network().getForecastForFiveDays(lat, lon)
                    .map(this::saveForecastToCash);
        } else {
            return Single.just(localStorage.getSavedWeatheForecast(lat, lon));
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
    public void addPlaceToRecent(Place place) {
        localStorage.addCityToResent(Utils.convertPlaceToPlaceData(place));
    }

    @Override
    public void removeFromRecent(PlaceData place) {
        localStorage.deleteCityFromResent(place);
    }
}
