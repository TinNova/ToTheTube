package com.example.tin.tothetube.model;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public interface NetworkListener {

    interface StationsListener {
        void getStationArrayList(ArrayList<Station> stations) throws MalformedURLException;
    }

    interface ArrivalsListener {
        void getArrivalsArrayList(ArrayList<Arrival> arrivals);
    }

}
