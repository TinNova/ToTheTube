package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tin.tothetube.model.Station;
import com.example.tin.tothetube.model.StationAdapter;
import com.example.tin.tothetube.model.StationPositionListener;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, StationPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private StationAdapter mAdapter;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mainPresenter = new MainPresenter(this);
            mainPresenter.getAllStations(MainActivity.this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_stationList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new StationAdapter(null, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

    }

    ArrayList<Station> mStation;

    @Override
    public void showStation(ArrayList<Station> stations) {
        mStation = stations;
        mAdapter.setStation(stations);
    }

    @Override
    public void tvArrivalTime0OnClick(View v, int position) {

        Toast.makeText(this, "Position for 1st is " + position + " Train Line: "
                + mStation.get(position).getArrivals().get(0).getLineName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStation.get(position).getArrivals().get(0).getTimeToStation());
        intent.putExtra("LineName", mStation.get(position).getArrivals().get(0).getLineName());
        intent.putExtra("LineId", mStation.get(position).getArrivals().get(0).getLineId());

        startActivity(intent);
    }

    @Override
    public void tvArrivalTime1OnClick(View v, int position) {

        Toast.makeText(this, "Position for 2nd is " + position + " Train Line: "
                + mStation.get(position).getArrivals().get(1).getLineName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStation.get(position).getArrivals().get(1).getTimeToStation());
        intent.putExtra("LineName", mStation.get(position).getArrivals().get(1).getLineName());
        intent.putExtra("LineId", mStation.get(position).getArrivals().get(1).getLineId());

        startActivity(intent);
    }

    @Override
    public void tvArrivalTime2OnClick(View v, int position) {

        Toast.makeText(this, "Position for 3rd is " + position + " Train Line: "
                + mStation.get(position).getArrivals().get(2).getLineName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStation.get(position).getArrivals().get(2).getTimeToStation());
        intent.putExtra("LineName", mStation.get(position).getArrivals().get(2).getLineName());
        intent.putExtra("LineId", mStation.get(position).getArrivals().get(2).getLineId());

        startActivity(intent);
    }
}
