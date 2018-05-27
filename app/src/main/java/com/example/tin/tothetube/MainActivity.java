package com.example.tin.tothetube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tin.tothetube.model.Station;
import com.example.tin.tothetube.model.StationAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

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
        mAdapter = new StationAdapter(null, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showStation(ArrayList<Station> stations) {
        mAdapter.setStation(stations);

    }
}
