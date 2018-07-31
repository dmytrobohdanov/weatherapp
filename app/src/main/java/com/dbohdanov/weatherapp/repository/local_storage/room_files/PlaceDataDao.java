package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 *
 */
@Dao
public interface PlaceDataDao {
    @Query("SELECT * FROM placedata")
    Flowable<List<PlaceData>> getAll();

    @Query("SELECT * FROM placedata WHERE city_name LIKE (:cityName)")
    Flowable<PlaceData> getPlaceCityName(String cityName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlaceData placeData);

    @Delete
    void delete(PlaceData placeData);
}
