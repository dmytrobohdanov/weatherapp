package com.dbohdanov.weatherapp.ui.weather_activity;

/**
 *
 */
public interface IWeatherView {
    void showWaitingDialog();
    void hideWaitingDialog();

    void showError(String errorMessage);

    void setCityName(String cityName);
}
