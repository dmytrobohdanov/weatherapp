package com.dbohdanov.weatherapp.di;

import com.dbohdanov.weatherapp.repository.local_storage.LocalStorage;

import javax.inject.Singleton;

import dagger.Component;

/**
 * todo
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(LocalStorage localStorage);
}
