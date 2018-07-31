package com.dbohdanov.weatherapp.ui.main_activity;

import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;

/**
 *
 */
public interface IMainView {
    void showWeatherForPlace(PlaceData place);
}
