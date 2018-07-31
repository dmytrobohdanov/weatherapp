package com.dbohdanov.weatherapp.presenter.main_activity;

import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import com.dbohdanov.weatherapp.ui.main_activity.IMainView;

/**
 *
 */
public interface IMainActivityPresenter {
    void initRv(RecyclerView citiesList);

    void setView(IMainView mainView);

    void initPlacesFragment(FragmentManager fragmentManager);
}