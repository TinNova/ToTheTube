package com.example.tin.tothetube.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public class Station implements Parcelable {

    // What we need from the Stops API:
    // https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanMetroStation&radius=1000&modes=tube&categories=none&lat=51.514&lon=-0.122&app_id=0c37c519&app_key=89d67eb25bf2a3e6134f63fb82c72d5c

    private String naptanId; // Id of the station. REQUIRED for the Arrival Url
    private String commonName; // Human name of the station
    private double distance; // How far away the station in (in metres probably?)
    private double lat; // The coordinates of the station
    private double lon; // The coordinates of the station
    private ArrayList<Arrival> arrivals; // Arraylist of Arrival times for this station

    public Station(String naptanId, String commonName, double distance, double lat, double lon, ArrayList<Arrival> arrivals) {
        this.naptanId = naptanId;
        this.commonName = commonName;
        this.distance = distance;
        this.lat = lat;
        this.lon = lon;
        this.arrivals = arrivals;
    }

    protected Station(Parcel in) {
        naptanId = in.readString();
        commonName = in.readString();
        distance = in.readDouble();
        lat = in.readDouble();
        lon = in.readDouble();
        arrivals = in.createTypedArrayList(Arrival.CREATOR);

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(naptanId);
        dest.writeString(commonName);
        dest.writeDouble(distance);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeTypedList(arrivals);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

    public double getDistance() {
        return distance;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public ArrayList<Arrival> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<Arrival> arrivals) {
        this.arrivals = arrivals;
    }
}
