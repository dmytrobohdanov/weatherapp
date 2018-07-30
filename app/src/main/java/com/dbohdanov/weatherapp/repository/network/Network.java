package com.dbohdanov.weatherapp.repository.network;

import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.dbohdanov.weatherapp.repository.data_models.WeatherResponseModel;
import com.dbohdanov.weatherapp.repository.network.retrofit.RetrofitRequestPerformer;
import com.dbohdanov.weatherapp.utils.Constants;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 *
 */
public class Network implements INetwork {
    @Override
    public Single<DataWeatherForecast> getForecastForFiveDays(double lat, double lon) {
        return RetrofitRequestPerformer.getWeatherForFiveDays(lat, lon)
                .map(this::convertWeatherFormatData);

    }

    private DataWeatherForecast convertWeatherFormatData(WeatherResponseModel weatherResponseModel) {
        //checking for error response
        if (weatherResponseModel.getCod() == 200 || weatherResponseModel.getCod() == 201) {
            ArrayList<DataWeatherForecast.WeatherItem> weatherItems = new ArrayList<>();

            for (WeatherResponseModel.ResponseWeatherItem responseWeatherItem : weatherResponseModel.getResponseWeatherItems()) {
                weatherItems.add(
                        new DataWeatherForecast.WeatherItem()
                                .setTime(responseWeatherItem.getDt())
                                .setPressure(responseWeatherItem.getMain().getPressure())
                                .setHumidity(responseWeatherItem.getMain().getHumidity())
                                .setTemperature((int)(responseWeatherItem.getMain().getTemp() - Constants.KELVIN_CELSIUS_DIFF))
                                .setMain(responseWeatherItem.getWeather().get(0).getMain())
                                .setDescription(responseWeatherItem.getWeather().get(0).getDescription())
                                .setIconId(responseWeatherItem.getWeather().get(0).getIcon()));
            }

            return new DataWeatherForecast()
                    .setCoordinates(weatherResponseModel.getCity().getCoord())
                    .setCityName(weatherResponseModel.getCity().getName())
                    .setWeatherItems(weatherItems)
                    .setIsOnlineData(true);

        } else {
            //case of some error
            return new DataWeatherForecast(weatherResponseModel.getMessage());
        }
    }
}
