package com.example.tin.tothetube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tin.tothetube.model.Line;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.tothetube.MainActivity.LINE_NAME;


public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {

    private static final String TAG = DetailActivity.class.getSimpleName();

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
            detailPresenter.getAllLines(DetailActivity.this, line);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void showLines(ArrayList<Line> lines) {

        Log.d(TAG, "showLines: " + lines);
    }

}
