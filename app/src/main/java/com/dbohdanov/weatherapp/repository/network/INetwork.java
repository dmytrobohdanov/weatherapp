package com.dbohdanov.weatherapp.repository.network;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

import io.reactivex.Single;

/**
 *
 */
public interface INetwork {
    /**
     * Getting weather forecast data for 5 days and
     * converts response data model to inner app weather data format
     */
    Single<DataWeatherForecast> getForecastForFiveDays(double lat, double lon);
}
