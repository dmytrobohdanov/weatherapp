package com.dbohdanov.weatherapp.repository.network.retrofit;

import com.dbohdanov.weatherapp.repository.data_models.WeatherResponseModel;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * perform request to API
 */
public class RetrofitRequestPerformer {
    public static final String TAG = "debugTag";


    private static final String QUERY_PARAM_API_KEY = "appid";
    private static final String QUERY_PARAM_LAT = "lat";
    private static final String QUERY_PARAM_LON = "lon";

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "4ef21c1a18da5cebcb9d8d4ea810983b";


    private static ApiService buildRetrofitClient() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);
    }

    /**
     * Performing request to API
     */
    public static Single<WeatherResponseModel> getWeatherForFiveDays(double lat, double lon) {
        HashMap<String, String> params = new HashMap<>();
        params.put(QUERY_PARAM_API_KEY, API_KEY);
        params.put(QUERY_PARAM_LAT, String.valueOf(lat));
        params.put(QUERY_PARAM_LON, String.valueOf(lon));

        return buildRetrofitClient()
                .getWeatherForFiveDays(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
