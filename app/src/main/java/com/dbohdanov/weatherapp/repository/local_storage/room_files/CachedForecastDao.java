package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 */
@Dao
public interface CachedForecastDao {
    @Query("SELECT * FROM cachedforecast")
    Flowable<List<CachedForecast>> getAll();

    @Query("SELECT * FROM cachedforecast WHERE city_name = :cityName")
    Single<CachedForecast> getForecast(String cityName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CachedForecast cachedForecast);

    @Delete
    void delete(CachedForecast cachedForecast);
}
