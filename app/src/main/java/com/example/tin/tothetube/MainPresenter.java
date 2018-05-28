package com.example.tin.tothetube;

import android.content.Context;
import android.util.Log;

import com.example.tin.tothetube.model.Arrival;
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

    Context mcontext;

    @Override
    public void getAllStations(Context context) throws MalformedURLException {

        String stationUrl = NetworkUtils.getRadiusUrl(DEFAULT_LAT, DEFAULT_LON);

        mcontext = context;
        /*
         * Use the String URL "weatherRequestUrl" to request the JSON from the server
         * and parse it
         */
        NetworkConnection.getInstance(context).getStationResponseFromHttpUrl(stationUrl, new NetworkListener.StationsListener() {
            @Override
            public void getStationArrayList(ArrayList<Station> stations) throws MalformedURLException {

//                /* Show weather on screen */
//                mainView.showStation(stations);

                /* Taking the naptanId from every station item and create a list of Arrival URLs */
//                ArrayList<String> napIds = new ArrayList<>();
//                for (int i = 0; i < stations.size(); i++) {
//                    String arrivalUrl = NetworkUtils.getArrivalsUrl(stations.get(i).getNaptanId());
//                    napIds.add(arrivalUrl);
//                }

                getAllArrivalTimes(mcontext, stations);

            }
        });
    }

    @Override
    public void getAllArrivalTimes(Context context, final ArrayList<Station> stations) throws MalformedURLException {

        for (int i = 0; i < stations.size(); i++) {

            final Station mStation = stations.get(i);

            String arrivalUrl = NetworkUtils.getArrivalsUrl(mStation.getNaptanId());

            NetworkConnection.getInstance(context).getArrivalTimesResponse(arrivalUrl, new NetworkListener.ArrivalsListener() {


                @Override
                public void getArrivalsArrayList(ArrayList<Arrival> arrivals) {

                    Log.d(TAG, "arrivals List:" + arrivals);

                    mStation.setTimeOfArrival0(arrivals.get(0).getTimeToStation());
                    mStation.setTimeOfArrival1(arrivals.get(1).getTimeToStation());
                    mStation.setTimeOfArrival2(arrivals.get(2).getTimeToStation());

                    Log.d(TAG, "Station with Arrival Times: " + mStation);

                }
            });
        }

        /* Show weather on screen */
        mainView.showStation(stations);

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
