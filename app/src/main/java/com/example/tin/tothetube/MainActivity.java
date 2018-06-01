package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tin.tothetube.model.models.Station;
import com.example.tin.tothetube.model.adapters.StationAdapter;
import com.example.tin.tothetube.model.StationPositionListener;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.DetailActivity.SAVED_INSTANT_STATE_KEY;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, StationPositionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    /* Keys for the Intent */
    public String TIME_TO_ARRIVAL = "TimeToArrival";
    public static final String LINE_NAME = "LineName";
    public static final String STATION_ID = "StationId";
    public String LINE_ID = "LineId";

    private ProgressBar mLoadingIndicator;
    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private StationAdapter mAdapter;

    private MainPresenter mainPresenter;

    private ArrayList<Station> mStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_stationList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new StationAdapter(null, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        mLoadingIndicator = findViewById(R.id.pB_loading_indicator_main);

        /* If There isn't a savedInstanceState, Download The Data And Build The RecyclerView */
        if (savedInstanceState != null) {

            /* Retrieve the mWeather ArrayList from onSavedInstanceState */
            mStations = savedInstanceState.getParcelableArrayList(SAVED_INSTANT_STATE_KEY);
            /* Pass the mWeather ArrayList to the adapter */
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setStation(mStations);
            hideLoading();
        } else {


            try {
                mainPresenter = new MainPresenter(this);
                mainPresenter.getAllStations(MainActivity.this);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showStation(ArrayList<Station> stations) {
        mStations = stations;
        mAdapter.setStation(stations);
        hideLoading();

    }

    @Override
    public void tvArrivalTime0OnClick(View v, int position) {

        Log.d(TAG, "Position for 1st is " + position + " Train Line: "
                + mStations.get(position).getArrivals().get(1).getLineName());

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStations.get(position).getArrivals().get(0).getTimeToStation());
        intent.putExtra(LINE_NAME, mStations.get(position).getArrivals().get(0).getLineName());
        intent.putExtra("LineId", mStations.get(position).getArrivals().get(0).getLineId());
        intent.putExtra(STATION_ID, mStations.get(position).getNaptanId());

        startActivity(intent);
    }

    @Override
    public void tvArrivalTime1OnClick(View v, int position) {

        Log.d(TAG, "Position for 2nd is " + position + " Train Line: "
                + mStations.get(position).getArrivals().get(1).getLineName());

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStations.get(position).getArrivals().get(1).getTimeToStation());
        intent.putExtra(LINE_NAME, mStations.get(position).getArrivals().get(1).getLineName());
        intent.putExtra("LineId", mStations.get(position).getArrivals().get(1).getLineId());
        intent.putExtra(STATION_ID, mStations.get(position).getNaptanId());

        startActivity(intent);
    }

    @Override
    public void tvArrivalTime2OnClick(View v, int position) {

        Log.d(TAG, "Position for 3rd is " + position + " Train Line: "
                + mStations.get(position).getArrivals().get(1).getLineName());

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TimeToArrival", mStations.get(position).getArrivals().get(2).getTimeToStation());
        intent.putExtra(LINE_NAME, mStations.get(position).getArrivals().get(2).getLineName());
        intent.putExtra("LineId", mStations.get(position).getArrivals().get(2).getLineId());
        intent.putExtra(STATION_ID, mStations.get(position).getNaptanId());

        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /* Saving mWeather to be reused should the device rotate */
        outState.putParcelableArrayList(SAVED_INSTANT_STATE_KEY, mStations);
    }
}
