package com.dbohdanov.weatherapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.data_models.CustomPlace;
import com.dbohdanov.weatherapp.utils.Constants;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class WeatherActivity extends AppCompatActivity implements IWeatherView {
    private WeatherDataViewModel weatherDataViewModel;
    private ProgressBar progressBar;
    private RecyclerView weatherForecastsRecycle;
    private TextView tvCityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        progressBar = findViewById(R.id.progressBar);
        weatherForecastsRecycle = findViewById(R.id.weather_activity_rv);
        tvCityName = findViewById(R.id.weather_activity_city_name);

        weatherDataViewModel = ViewModelProviders.of(this).get(WeatherDataViewModel.class);

        weatherDataViewModel.getWeatherActivityPresenter().setView(this);
        weatherDataViewModel.getWeatherActivityPresenter().initRv(weatherForecastsRecycle);


        //getting place from main activity and get weather for this place
        Log.d("taag", "onCreate: " + getIntent().getStringExtra(Constants.KEY_BUNDLE_PLACE));
        CustomPlace place = new Gson().fromJson(getIntent().getStringExtra(Constants.KEY_BUNDLE_PLACE), CustomPlace.class);
        weatherDataViewModel.getWeatherActivityPresenter().showForecastForFiveDays(place);
    }

    @Override
    public void showWaitingDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWaitingDialog() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Log.d("taag", "showError: " + errorMessage);
        Toast.makeText(this, "error: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCityName(String cityName) {
        tvCityName.setText(cityName);
    }
}
