package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.NetworkConnection;
import com.example.tin.tothetube.model.NetworkListener;
import com.example.tin.tothetube.model.NetworkUtils;
import com.example.tin.tothetube.model.Station;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.model.NetworkUtils.DEFAULT_LAT;
import static com.example.tin.tothetube.model.NetworkUtils.DEFAULT_LON;


public class MainPresenter implements MainContract.MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.MainView mainView;

    MainPresenter(MainContract.MainView view) throws MalformedURLException {
        this.mainView = view;
    }

    @Override
    public void getAllStations(Context context) throws MalformedURLException {

        String url = NetworkUtils.getRadiusUrl(DEFAULT_LAT, DEFAULT_LON);

        /*
         * Use the String URL "weatherRequestUrl" to request the JSON from the server
         * and parse it
         */
        NetworkConnection.getInstance(context).getStationResponseFromHttpUrl(url, new NetworkListener.StationsListener() {
            @Override
            public void getStationArrayList(ArrayList<Station> stations) {

                /* Show weather on screen */
                mainView.showStation(stations);

                /** NOW MAKE A NETWORK CONNECTION TO GET THE ARRIVAL TIMES! */
            }
        });
    }
}
