package com.example.tin.tothetube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    // API For Stops on a line
//    https://api.tfl.gov.uk/Line/victoria/Route/Sequence/outbound?serviceTypes=Regular

    // This one looks better, it contains IDs AND human names for the stations
//    https://api.tfl.gov.uk/Line/victoria/StopPoints?tflOperatedNationalRailStationsOnly=false


}
