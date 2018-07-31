package com.dbohdanov.weatherapp.utils;

import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.google.android.gms.location.places.Place;

/**
 *
 */
public class Utils {
    public static PlaceData convertPlaceToPlaceData(Place place) {
        return new PlaceData(place.getName().toString(),
                place.getLatLng().latitude,
                place.getLatLng().longitude);
    }
}
