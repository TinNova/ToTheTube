package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.Station;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public interface MainContract {

    interface MainView {

        void showStation(ArrayList<Station> stations);
    }

    interface MainPresenter {

        void getAllStations(Context context) throws MalformedURLException;
    }
}
