package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 *
 */
public class WeatherActivityPresenter implements IWeatherActivityPresenter {
    Context applicationContext;

    public WeatherActivityPresenter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = ((ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
