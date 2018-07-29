package com.dbohdanov.weatherapp.repository.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Cutted version of server response
 */
public class WeatherResponseModel {
    private int cod;
    private String message;
    private int cnt;
    private City city;
    @SerializedName("list")
    private ArrayList<ResponseWeatherItem> responseWeatherItems;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<ResponseWeatherItem> getResponseWeatherItems() {
        return responseWeatherItems;
    }

    public void setResponseWeatherItems(ArrayList<ResponseWeatherItem> responseWeatherItems) {
        this.responseWeatherItems = responseWeatherItems;
    }


    public static class City {
        /**
         * id : 1907296
         * name : Tawarano
         * coord : {"lat":35.0164,"lon":139.0077}
         * country : none
         */

        private int id;
        private String name;
        private Coordinates coord;
        private String country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coordinates getCoord() {
            return coord;
        }

        public void setCoord(Coordinates coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    public static class Coordinates {
        /**
         * lat : 35.0164
         * lon : 139.0077
         */

        private double lat;
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    public static class ResponseWeatherItem {
        private int dt;
        private String dt_txt;
        private WeatherMain main;
        private ArrayList<Weather> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public WeatherMain getMain() {
            return main;
        }

        public void setMain(WeatherMain main) {
            this.main = main;
        }

        public ArrayList<Weather> getWeather() {
            return weather;
        }

        public void setWeather(ArrayList<Weather> weather) {
            this.weather = weather;
        }
    }

    public static class WeatherMain {
        /**
         * temp : 283.76
         * temp_min : 283.76
         * temp_max : 283.761
         * pressure : 1017.24
         * sea_level : 1026.83
         * grnd_level : 1017.24
         * humidity : 100
         * temp_kf : 0
         */

        private double temp;
        private double temp_min;
        private double temp_max;
        private double pressure;
        private double sea_level;
        private double grnd_level;
        private int humidity;
        private int temp_kf;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getSea_level() {
            return sea_level;
        }

        public void setSea_level(double sea_level) {
            this.sea_level = sea_level;
        }

        public double getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(double grnd_level) {
            this.grnd_level = grnd_level;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getTemp_kf() {
            return temp_kf;
        }

        public void setTemp_kf(int temp_kf) {
            this.temp_kf = temp_kf;
        }
    }

    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

}
