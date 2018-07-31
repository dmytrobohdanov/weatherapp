package com.dbohdanov.weatherapp.ui.weather_activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.utils.Constants;
import com.google.gson.Gson;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weatherDataViewModel = ViewModelProviders.of(this).get(WeatherDataViewModel.class);

        weatherDataViewModel.getWeatherActivityPresenter().setView(this);
        weatherDataViewModel.getWeatherActivityPresenter().initRv(weatherForecastsRecycle);

        //getting place from main activity and get weather for this place
        PlaceData place = new Gson().fromJson(getIntent().getStringExtra(Constants.KEY_BUNDLE_PLACE), PlaceData.class);
        weatherDataViewModel.setPlace(place);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather_act_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                    this.finish();
                onBackPressed();
                return true;

            case R.id.menu_renew:
                weatherDataViewModel
                        .getWeatherActivityPresenter()
                        .showForecastForFiveDays(
                                weatherDataViewModel.getPlace());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
