package com.dbohdanov.weatherapp.ui.weather_activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dbohdanov.weatherapp.presenter.weather_activity.IWeatherActivityPresenter;
import com.dbohdanov.weatherapp.presenter.weather_activity.WeatherActivityPresenter;

/**
 * viewmodel for weather data activity
 */
public class WeatherDataViewModel extends AndroidViewModel{
    private IWeatherActivityPresenter weatherActivityPresenter;

    public WeatherDataViewModel(@NonNull Application application) {
        super(application);
        weatherActivityPresenter = new WeatherActivityPresenter(application.getApplicationContext());
    }

    public IWeatherActivityPresenter getWeatherActivityPresenter() {
        return weatherActivityPresenter;
    }
}
