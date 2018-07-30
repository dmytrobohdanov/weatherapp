package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.support.v7.widget.RecyclerView;

import com.dbohdanov.weatherapp.repository.data_models.CustomPlace;
import com.dbohdanov.weatherapp.ui.IWeatherView;

/**
 *
 */
public interface IWeatherActivityPresenter {
    void showForecastForFiveDays(CustomPlace place);

    void setView(IWeatherView weatherView);

    void initRv(RecyclerView weatherForecastsRecycle);
}
