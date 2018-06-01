package com.example.tin.tothetube;

import android.content.Context;
import android.util.Log;

import com.example.tin.tothetube.model.models.Arrival;
import com.example.tin.tothetube.model.network.NetworkConnection;
import com.example.tin.tothetube.model.network.NetworkListener;
import com.example.tin.tothetube.model.network.NetworkUtils;
import com.example.tin.tothetube.model.models.Station;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.model.network.NetworkUtils.DEFAULT_LAT;
import static com.example.tin.tothetube.model.network.NetworkUtils.DEFAULT_LON;


public class MainPresenter implements MainContract.MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.MainView mainView;

    /* Used to help exit the for loop within the NetworkConnection class within the method
     * getArrivalTimesResponse */
    private int i;

    MainPresenter(MainContract.MainView view) throws MalformedURLException {
        this.mainView = view;
    }

    private Context mcontext;

    @Override
    public void getAllStations(Context context) throws MalformedURLException {

        String stationUrl = NetworkUtils.getRadiusUrl(DEFAULT_LAT, DEFAULT_LON);

        mcontext = context;
        mainView.showLoading();

        /*
         * Use the String URL "weatherRequestUrl" to request the JSON from the server
         * and parse it
         */
        NetworkConnection.getInstance(context).getStationResponseFromHttpUrl(stationUrl, new NetworkListener.StationsListener() {
            @Override
            public void getStationArrayList(ArrayList<Station> stations) throws MalformedURLException {

                getAllArrivalTimes(mcontext, stations);

            }
        });
    }



    @Override
    public void getAllArrivalTimes(Context context, final ArrayList<Station> stations) throws MalformedURLException {

            i = 0;

            NetworkConnection.getInstance(context).getArrivalTimesResponse(stations, new NetworkListener.ArrivalsListener() {

                @Override
                public void getArrivalsArrayList(ArrayList<Arrival> arrivals, Station station) {

                    i++;
                    Log.d(TAG, "arrivals List:" + arrivals);

                    /* Adding Arrival Time ArrayList to Station ArrayList */
                    station.setArrivals(arrivals);

                    Log.d(TAG, "Station with Arrival Times: " + station);

                    Log.d(TAG, "i 1: " + i);
                    /* Helper to exit the for loop within the NetworkConnection, when i == the number
                    * of stations in the stations arrayList which in this case is 3 it sends the stations
                     * to the MainActivity*/
                    if (stations.size() == i) {

                        Log.d(TAG, "stations: " + stations);
                        mainView.showStation(stations);

                    }
                }
            });
    }


    //TODO:
    /** NOW MAKE A NETWORK CONNECTION TO GET THE ARRIVAL TIMES! */
    /** Display the arrival times under each station */
    //--OR--//
    /** Get the ArrayList of Arrival Times
     * Only after the Arrival times ArrayList has been build should we add the Station
     * and Arrival times to the Adapter. Then in onBindViewHolder make sure we add the
     * Station followed by that stations three arrival times.
     * Then the onClick can be handled in the presenter, see StarWars example for this. */
}
