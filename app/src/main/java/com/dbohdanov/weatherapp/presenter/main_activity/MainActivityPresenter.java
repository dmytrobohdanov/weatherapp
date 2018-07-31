package com.dbohdanov.weatherapp.presenter.main_activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.Repository;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.ui.main_activity.IMainView;
import com.dbohdanov.weatherapp.utils.Utils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;

/**
 *
 */
public class MainActivityPresenter implements IMainActivityPresenter {
    private Repository repository;
    private Context applicationContext;
    private IMainView mainView;
    private PlacesListAdapter placesListAdapter;

    public MainActivityPresenter(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.repository = new Repository();
    }

    @SuppressLint("CheckResult")
    @Override
    public void initRv(RecyclerView rvCitiesList) {
        placesListAdapter = new PlacesListAdapter(new ArrayList<>(), placeData -> mainView.showWeatherForPlace(placeData));
        rvCitiesList.setLayoutManager(new LinearLayoutManager(applicationContext));
        rvCitiesList.setAdapter(placesListAdapter);

        //swipe do delete
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                repository.removeFromRecent(placesListAdapter.getItem(viewHolder.getAdapterPosition()));
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvCitiesList);

        repository.getListOfResentCities()
                .subscribe(placesListAdapter::setItems);
    }

    @Override
    public void setView(IMainView mainView) {
        this.mainView = mainView;
    }


    @Override
    public void initPlacesFragment(FragmentManager fragmentManager) {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                fragmentManager.findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                handleNewPlace(Utils.convertPlaceToPlaceData(place));
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d("tag", "An error occurred: " + status);
            }
        });
    }

    private void handleNewPlace(PlaceData place) {
//        repository.addPlaceToRecent(place);
        mainView.showWeatherForPlace(place);
    }
}
