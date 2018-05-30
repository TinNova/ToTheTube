package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tin.tothetube.model.Line;
import com.example.tin.tothetube.model.adapters.LineAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.MainActivity.LINE_NAME;


public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private DetailPresenter detailPresenter;
    private String line;

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

        try {
            detailPresenter.getAllLines(DetailActivity.this, line);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_lineList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new LineAdapter(null, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLines(ArrayList<Line> lines) {
        mAdapter.setLines(lines);

        Log.d(TAG, "showLines: " + lines);
    }
}
