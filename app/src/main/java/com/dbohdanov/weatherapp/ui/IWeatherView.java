package com.dbohdanov.weatherapp.ui;

/**
 *
 */
public interface IWeatherView {
    void showWaitingDialog();
    void hideWaitingDialog();

    void showError(String errorMessage);

    void setCityName(String cityName);
}
