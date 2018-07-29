package com.dbohdanov.weatherapp.repository.local_storage;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;

/**
 *
 */
public class LocalStorage implements ILocalStorage{

    @Override
    public void saveForecast(DataWeatherForecast dataWeatherForecast) {
        dataWeatherForecast.setIsOnlineData(false);

        //todo
    }
}
