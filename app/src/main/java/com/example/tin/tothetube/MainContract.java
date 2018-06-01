package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.models.Station;

import java.net.MalformedURLException;
import java.util.ArrayList;


interface MainContract {

    interface MainView {

        void showStation(ArrayList<Station> stations);

        void showLoading();

        void hideLoading();
    }

    interface MainPresenter {

        void getAllStations(Context context) throws MalformedURLException;

        void getAllArrivalTimes(Context context, ArrayList<Station> stations) throws MalformedURLException;
    }
}
