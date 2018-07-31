package com.dbohdanov.weatherapp.repository.local_storage;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.dbohdanov.weatherapp.App;
import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.AppDatabase;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.CachedForecast;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.CachedForecastDao;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceDataDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class LocalStorage implements ILocalStorage {
    private static AppDatabase database = null;

    @Inject
    Context context;

    private PlaceDataDao dbDao;
    private CachedForecastDao cachedForecastDao;

    public LocalStorage() {
        App.getAppComponent().inject(this);

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-name").build();
        }
        dbDao = database.placeDataDao();
        cachedForecastDao = database.cachedForecastDao();
    }

    @Override
    public Flowable<List<PlaceData>> getListOfResentCities() {
        return dbDao.getAll()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteCityFromResent(PlaceData placeData) {
        Completable.fromAction(() -> dbDao.delete(placeData))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void addCityToResent(PlaceData placeData) {
        Completable.fromAction(() -> dbDao.insert(placeData))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<DataWeatherForecast> getSavedWeatherForecast(String cityName) {
        return cachedForecastDao.getForecast(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(cachedForecast -> Single.just(cachedForecast.getDataWeatherForecast()));
    }

    @Override
    public void cleanOutofdateData() {
        //todo
    }

    @Override
    public void saveForecast(DataWeatherForecast dataWeatherForecast) {
        dataWeatherForecast.setIsOnlineData(false);

        CachedForecast cachedForecast = new CachedForecast();
        cachedForecast.setCityName(dataWeatherForecast.getCityName());
        cachedForecast.setDataWeatherForecast(dataWeatherForecast);

        Completable.fromAction(() -> cachedForecastDao.insert(cachedForecast))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
