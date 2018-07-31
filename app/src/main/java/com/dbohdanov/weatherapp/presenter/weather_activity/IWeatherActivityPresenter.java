package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.ui.weather_activity.IWeatherView;

/**
 *
 */
public interface IWeatherActivityPresenter {
    @SuppressLint("CheckResult")
    void showForecastForFiveDays(PlaceData place);

    void setView(IWeatherView weatherView);

    void initRv(RecyclerView weatherForecastsRecycle);
}
