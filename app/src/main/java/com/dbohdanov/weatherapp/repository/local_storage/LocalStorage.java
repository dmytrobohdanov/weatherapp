package com.dbohdanov.weatherapp.repository.local_storage;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dbohdanov.weatherapp.App;
import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.AppDatabase;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceDataDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class LocalStorage implements ILocalStorage {
    @Inject
    Context context;

    private PlaceDataDao dbDao;

    public LocalStorage() {
        App.getAppComponent().inject(this);
        dbDao = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database-name").build().placeDataDao();
    }

    @Override
    public Flowable<List<PlaceData>> getListOfResentCities() {
        return dbDao.getAll()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteCityFromResent(PlaceData placeData) {
        dbDao.delete(placeData);
    }

    @Override
    public void addCityToResent(PlaceData placeData) {
        Completable.fromAction(() -> dbDao.insert(placeData))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public DataWeatherForecast getSavedWeatheForecast(double lat, double lon) {
        return null;
    }

    @Override
    public void cleanOutofdateData() {

    }

    @Override
    public void saveForecast(DataWeatherForecast dataWeatherForecast) {
        dataWeatherForecast.setIsOnlineData(false);

        //todo
    }
}
