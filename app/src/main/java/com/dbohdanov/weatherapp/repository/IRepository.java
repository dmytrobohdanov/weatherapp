package com.dbohdanov.weatherapp.repository;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

import io.reactivex.Single;

/**
 *
 */
public interface IRepository {
    Single<DataWeatherForecast> getForecastForFiveDays(boolean isNetworkAvailable, double lat, double lon);

}
