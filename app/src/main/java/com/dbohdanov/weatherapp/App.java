package com.dbohdanov.weatherapp;

import android.app.Application;

import com.dbohdanov.weatherapp.di.AppComponent;
import com.dbohdanov.weatherapp.di.AppModule;
import com.dbohdanov.weatherapp.di.DaggerAppComponent;

/**
 * application class
 */
public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    public AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
