package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 *
 */
@Database(entities = {PlaceData.class, CachedForecast.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlaceDataDao placeDataDao();

    public abstract CachedForecastDao cachedForecastDao();
}
