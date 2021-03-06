package com.example.tin.tothetube.model.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Arrival implements Parcelable {

    private final String lineId; // // Needed for when the arrival time is clicked on to show the line it's connected to in DetailActivity
    private final String lineName;
    private final long timeToStation; // Needed to find the next three trains and their arrival times

    public Arrival(String lineId, String lineName, long timeToStation) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.timeToStation = timeToStation;
    }

    private Arrival(Parcel in) {
        lineId = in.readString();
        lineName = in.readString();
        timeToStation = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lineId);
        dest.writeString(lineName);
        dest.writeLong(timeToStation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Arrival> CREATOR = new Creator<Arrival>() {
        @Override
        public Arrival createFromParcel(Parcel in) {
            return new Arrival(in);
        }

        @Override
        public Arrival[] newArray(int size) {
            return new Arrival[size];
        }
    };

    public String getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public long getTimeToStation() {
        return timeToStation;
    }
}
