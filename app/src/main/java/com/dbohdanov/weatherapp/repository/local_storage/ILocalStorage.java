package com.dbohdanov.weatherapp.repository.local_storage;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

/**
 *
 */
public interface ILocalStorage {

    void getListOfResentCities();

    DataWeatherForecast getSavedWeatheForecast(double lat, double lon);

    void cleanOutofdateData();

    void addToResent();

    void saveForecast(DataWeatherForecast dataWeatherForecast);
}
