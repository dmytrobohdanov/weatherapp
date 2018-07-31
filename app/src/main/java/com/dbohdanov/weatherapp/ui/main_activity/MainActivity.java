package com.dbohdanov.weatherapp.ui.main_activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.presenter.main_activity.IMainActivityPresenter;
import com.dbohdanov.weatherapp.ui.weather_activity.WeatherActivity;
import com.dbohdanov.weatherapp.utils.Constants;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements IMainView {
    public static final String TAG = "mainActTag";

    private MainActivityDataViewModel mainActivityDataViewModel;
    private RecyclerView recentCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recentCities = findViewById(R.id.main_activity_rv);

        mainActivityDataViewModel = ViewModelProviders.of(this).get(MainActivityDataViewModel.class);
        IMainActivityPresenter mainActivityPresenter = mainActivityDataViewModel.getMainActivityPresenter();
        mainActivityPresenter.setView(this);
        mainActivityPresenter.initRv(recentCities);
        mainActivityPresenter.initPlacesFragment(getFragmentManager());
    }

    @Override
    public void showWeatherForPlace(Place place) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra(Constants.KEY_BUNDLE_PLACE, new Gson().toJson(place));
        startActivity(intent);
    }
}
