package com.dbohdanov.weatherapp.repository.local_storage;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

/**
 *
 */
public class LocalStorage implements ILocalStorage{

    @Override
    public void getListOfResentCities() {

    }

    @Override
    public DataWeatherForecast getSavedWeatheForecast(double lat, double lon) {
        return null;
    }

    @Override
    public void cleanOutofdateData() {

    }

    @Override
    public void addToResent() {

    }

    @Override
    public void saveForecast(DataWeatherForecast dataWeatherForecast) {
        dataWeatherForecast.setIsOnlineData(false);

        //todo
    }
}
