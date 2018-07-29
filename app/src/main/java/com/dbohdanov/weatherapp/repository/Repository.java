package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.LocalStorage;
import com.dbohdanov.weatherapp.repository.network.Network;

import io.reactivex.Single;

/**
 *
 */
public class Repository implements IRepository {

    @Override
    public Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, double lat, double lon) {
        if (isNetworkAvailable) {
            return new Network().getForecastForFiveDays(lat, lon)
                    .map(this::saveForecastToCash);
        } else {
            return Single.just(new LocalStorage().getSavedWeatheForecast(lat, lon));
        }
    }

    private DataWeatherForecast saveForecastToCash(DataWeatherForecast dataWeatherForecast) {
        new LocalStorage().saveForecast(dataWeatherForecast);
        return dataWeatherForecast;
    }
}
