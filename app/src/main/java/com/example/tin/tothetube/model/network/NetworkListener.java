package com.example.tin.tothetube.model.network;

import com.example.tin.tothetube.model.models.Arrival;
import com.example.tin.tothetube.model.models.Line;
import com.example.tin.tothetube.model.models.Station;

import java.net.MalformedURLException;
import java.util.ArrayList;


public interface NetworkListener {

    interface StationsListener {
        void getStationArrayList(ArrayList<Station> stations) throws MalformedURLException;
    }

    interface ArrivalsListener {
        void getArrivalsArrayList(ArrayList<Arrival> arrivals, Station station);
    }

    interface LinesListener {
        void getLinesArrayList(ArrayList<Line> lines);
    }

}
