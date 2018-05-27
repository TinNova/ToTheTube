package com.example.tin.tothetube.model;

import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public interface NetworkListener {

    interface StationsListener {
        void getStationArrayList(ArrayList<Station> stations);
    }

    interface ArrivalsListener {
        void getArrivalsArrayList(ArrayList<Arrival> arrivals);
    }

}
