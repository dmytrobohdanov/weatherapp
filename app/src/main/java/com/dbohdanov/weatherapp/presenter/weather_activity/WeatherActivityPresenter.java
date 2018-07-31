package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.Repository;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.ui.weather_activity.IWeatherView;

import java.util.ArrayList;

/**
 *
 */
public class WeatherActivityPresenter implements IWeatherActivityPresenter {
    private Context applicationContext;
    private IWeatherView weatherView;
    private WeatherAdapter weatherAdapter;
    private Repository repository;

    public WeatherActivityPresenter(Context applicationContext) {
        this.applicationContext = applicationContext;
        repository = new Repository();
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
    public void showForecastForFiveDays(PlaceData place) {
        weatherView.showWaitingDialog();

        boolean isNetworkAvailable = isNetworkAvailable();
        repository.getForecastForFiveDays(
                isNetworkAvailable,
                place)
                .subscribe(dataWeatherForecast -> {
                            weatherView.hideWaitingDialog();

                            if (dataWeatherForecast.getErrorMessage() != null) {
                                weatherView.showError(dataWeatherForecast.getErrorMessage());
                            } else {
                                repository.addPlaceToRecent(new PlaceData(dataWeatherForecast.getCityName(),
                                        dataWeatherForecast.getLat(),
                                        dataWeatherForecast.getLon()));

                                weatherView.setCityName(dataWeatherForecast.getCityName());
                                weatherAdapter.setItems(dataWeatherForecast.getWeatherItems());

                                if (!isNetworkAvailable) {
                                    weatherView.showError(applicationContext.getResources().getString(R.string.offline));
                                }
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
