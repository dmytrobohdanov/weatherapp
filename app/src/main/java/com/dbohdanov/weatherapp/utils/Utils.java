package com.dbohdanov.weatherapp.utils;

import com.dbohdanov.weatherapp.repository.data_models.CustomPlace;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;

/**
 *
 */
public class Utils {
    public static CustomPlace convertPlaceToCustomePlace(Place place) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(place), CustomPlace.class);
    }

    public static PlaceData convertPlaceToPlaceData(Place place) {
        return new PlaceData(place.getName().toString(),
                place.getLatLng().latitude,
                place.getLatLng().latitude);
    }
}
