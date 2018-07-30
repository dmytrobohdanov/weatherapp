package com.dbohdanov.weatherapp.repository.network.retrofit;


import com.dbohdanov.weatherapp.repository.data_models.WeatherResponseModel;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Retrofit's
 */
public interface ApiService {
    @GET("forecast")
    Single<WeatherResponseModel> getWeatherForFiveDays(@QueryMap Map<String, String> options);
}
