package com.example.tin.tothetube.model.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tin on 30/05/2018.
 */

public class Line implements Parcelable {

    String naptanId;
    String commonName;

    public Line(String naptanId, String commonName) {
        this.naptanId = naptanId;
        this.commonName = commonName;
    }

    protected Line(Parcel in) {
        naptanId = in.readString();
        commonName = in.readString();
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(naptanId);
        parcel.writeString(commonName);
    }

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }
}
