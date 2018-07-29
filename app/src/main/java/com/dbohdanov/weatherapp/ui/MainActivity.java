package com.dbohdanov.weatherapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.IRepository;
import com.dbohdanov.weatherapp.utils.Constants;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "mainActTag";
    IRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                handleNewPlace(place);
                Log.d(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d(TAG, "An error occurred: " + status);
            }
        });
    }

    private void handleNewPlace(Place place) {
        //todo
//        repository.addPlaceToRecent(place);
        showWeatherForPlace(place);
    }

    private void showWeatherForPlace(Place place) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra(Constants.KEY_BUNDLE_PLACE, new Gson().toJson(place));
        startActivity(intent);
    }
}
