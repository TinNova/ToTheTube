package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.MalformedURLException;

import static com.example.tin.tothetube.MainActivity.LINE_NAME;


public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {

    private DetailPresenter detailPresenter;
    private String line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPresenter = new DetailPresenter(this);

        Intent getIntent = getIntent();

        line = getIntent.getStringExtra(LINE_NAME);

        try {
            detailPresenter.getLine(DetailActivity.this, line);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

    }

    // API For Stops on a line
//    https://api.tfl.gov.uk/Line/victoria/Route/Sequence/outbound?serviceTypes=Regular

    // This one looks better, it contains IDs AND human names for the stations
//    https://api.tfl.gov.uk/Line/victoria/StopPoints?tflOperatedNationalRailStationsOnly=false



}
