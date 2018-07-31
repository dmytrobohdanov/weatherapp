package com.dbohdanov.weatherapp.ui.main_activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.dbohdanov.weatherapp.presenter.main_activity.IMainActivityPresenter;
import com.dbohdanov.weatherapp.presenter.main_activity.MainActivityPresenter;

/**
 *
 */
public class MainActivityDataViewModel extends AndroidViewModel {
    IMainActivityPresenter mainActivityPresenter;

    public MainActivityDataViewModel(@NonNull Application application) {
        super(application);
        mainActivityPresenter = new MainActivityPresenter(application.getApplicationContext());
    }

    public IMainActivityPresenter getMainActivityPresenter() {
        return mainActivityPresenter;
    }
}
