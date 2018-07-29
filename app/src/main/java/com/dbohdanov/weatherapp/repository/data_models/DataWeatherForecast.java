package com.dbohdanov.weatherapp.repository.data_models;

import java.util.ArrayList;

/**
 *
 */
public class DataWeatherForecast {
    private String errorMessage;

    private double lat;
    private double lon;
    private String cityName;
    private ArrayList<WeatherItem> weatherItems;
    private boolean isOnlineData; //true if got from internet, false if got from cash

    public DataWeatherForecast() {
    }

    public DataWeatherForecast(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public String getCityName() {
        return cityName;
    }

    public ArrayList<WeatherItem> getWeatherItems() {
        return weatherItems;
    }

    public DataWeatherForecast setWeatherItems(ArrayList<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
        return this;
    }


    public DataWeatherForecast setCoordinates(WeatherResponseModel.Coordinates coordinates) {
        this.lat = coordinates.getLat();
        this.lon = coordinates.getLon();
        return this;
    }

    public DataWeatherForecast setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public DataWeatherForecast setIsOnlineData(boolean isOnlineData) {
        this.isOnlineData = isOnlineData;
        return this;
    }

    public static class WeatherItem {
        private int time;

        private String main;
        private String description;
        private String iconId;

        private double temperature;
        private double pressure;

        public int getTime() {
            return time;
        }

        public WeatherItem setTime(int time) {
            this.time = time;
            return this;
        }

        public String getMain() {
            return main;
        }

        public WeatherItem setMain(String main) {
            this.main = main;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public WeatherItem setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getIconId() {
            return iconId;
        }

        public WeatherItem setIconId(String iconId) {
            this.iconId = iconId;
            return this;
        }

        public double getTemperature() {
            return temperature;
        }

        public WeatherItem setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public double getPressure() {
            return pressure;
        }

        public WeatherItem setPressure(double pressure) {
            this.pressure = pressure;
            return this;
        }

        public double getHumidity() {
            return humidity;
        }

        public WeatherItem setHumidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        private double humidity;
    }
}
