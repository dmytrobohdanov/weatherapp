package com.dbohdanov.weatherapp.repository.local_storage.room_files;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 *
 */
@Entity
public class PlaceData {
    @PrimaryKey
    @ColumnInfo(name = "city_name")
    @NonNull
    private String name;

    @ColumnInfo(name = "city_latitude")
    private double latitude;

    @ColumnInfo(name = "city_longitude")
    private double longitude;

    public PlaceData() {
    }

    @Ignore
    public PlaceData(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
