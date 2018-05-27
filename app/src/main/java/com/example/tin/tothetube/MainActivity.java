package com.example.tin.tothetube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mainPresenter = new MainPresenter(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mainPresenter.getAllStations();

    }

}
