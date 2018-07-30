package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dbohdanov.weatherapp.repository.Repository;
import com.dbohdanov.weatherapp.repository.data_models.CustomPlace;
import com.dbohdanov.weatherapp.ui.IWeatherView;

import java.util.ArrayList;

/**
 *
 */
public class WeatherActivityPresenter implements IWeatherActivityPresenter {
    Context applicationContext;
    IWeatherView weatherView;
    private WeatherAdapter weatherAdapter;

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

    @SuppressLint("CheckResult")
    @Override
    public void showForecastForFiveDays(CustomPlace place) {
        weatherView.showWaitingDialog();

        new Repository().getForecastForFiveDays(
                isNetworkAvailable(),
                place.getLatLng().getLatitude(),
                place.getLatLng().getLongitude())
                .subscribe(dataWeatherForecast -> {
                            weatherView.hideWaitingDialog();

                            if (dataWeatherForecast.getErrorMessage() != null) {
                                weatherView.showError(dataWeatherForecast.getErrorMessage());
                            } else {
                                weatherView.setCityName(dataWeatherForecast.getCityName());
                                weatherAdapter.setItems(dataWeatherForecast.getWeatherItems());
                            }
                        },
                        throwable -> {
                            weatherView.hideWaitingDialog();
                            weatherView.showError(throwable.getMessage());
                        });
    }

    @Override
    public void setView(IWeatherView weatherView) {
        this.weatherView = weatherView;
    }

    @Override
    public void initRv(RecyclerView weatherForecastsRecycle) {
        weatherAdapter = new WeatherAdapter(applicationContext, new ArrayList<>());
        weatherForecastsRecycle.setLayoutManager(new LinearLayoutManager(applicationContext));
        weatherForecastsRecycle.setAdapter(weatherAdapter);
    }
}
