package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tin.tothetube.model.models.Line;
import com.example.tin.tothetube.model.adapters.LineAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.MainActivity.LINE_NAME;
import static com.example.tin.tothetube.MainActivity.STATION_ID;


public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private DetailPresenter detailPresenter;
    private String line;
    private String stationId;
    private ProgressBar mLoadingIndicator;
    private ArrayList<Line> mlines;

    /* Key to retrieve data saved within onSavedInstantState */
    public static final String SAVED_INSTANT_STATE_KEY = "saved_instant_state_key";

    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private LineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPresenter = new DetailPresenter(this);

        Intent getIntent = getIntent();

        line = getIntent.getStringExtra(LINE_NAME);
        stationId = getIntent.getStringExtra(STATION_ID);

        setTitle(line);

        Log.d(TAG, "Clicked On Station ID: " + stationId);

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_lineList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new LineAdapter(null, getApplicationContext(), stationId);
        mRecyclerView.setAdapter(mAdapter);

        mLoadingIndicator = findViewById(R.id.pB_loading_indicator_detail);

        /* If There isn't a savedInstanceState, Download The Data And Build The RecyclerView */
        if (savedInstanceState != null) {

            /* Retrieve the mWeather ArrayList from onSavedInstanceState */
            mlines = savedInstanceState.getParcelableArrayList(SAVED_INSTANT_STATE_KEY);
            /* Pass the mWeather ArrayList to the adapter */
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setLines(mlines);
            hideLoading();
        } else {

            try {
                detailPresenter.getAllLines(DetailActivity.this, line);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void showLines(ArrayList<Line> lines) {
        mlines = lines;
        mAdapter.setLines(lines);
        hideLoading();

        Log.d(TAG, "showLines: " + lines);
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
        outState.putParcelableArrayList(SAVED_INSTANT_STATE_KEY, mlines);
    }
}
