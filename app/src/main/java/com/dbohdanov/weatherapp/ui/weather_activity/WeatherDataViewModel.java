package com.dbohdanov.weatherapp.ui.weather_activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dbohdanov.weatherapp.presenter.weather_activity.IWeatherActivityPresenter;
import com.dbohdanov.weatherapp.presenter.weather_activity.WeatherActivityPresenter;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;

/**
 * viewmodel for weather data activity
 */
public class WeatherDataViewModel extends AndroidViewModel{
    private IWeatherActivityPresenter weatherActivityPresenter;
    private PlaceData place;

    public WeatherDataViewModel(@NonNull Application application) {
        super(application);
        weatherActivityPresenter = new WeatherActivityPresenter(application.getApplicationContext());
    }

    public IWeatherActivityPresenter getWeatherActivityPresenter() {
        return weatherActivityPresenter;
    }

    public PlaceData getPlace() {
        return place;
    }

    public void setPlace(PlaceData place) {
        this.place = place;
    }
}
