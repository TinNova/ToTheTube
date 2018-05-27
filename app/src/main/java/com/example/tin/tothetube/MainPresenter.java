package com.example.tin.tothetube;

import android.util.Log;

import com.example.tin.tothetube.model.utils.TflUrlUtils;
import com.example.tin.tothetube.model.utils.BaseUrlUtils;
import com.example.tin.tothetube.model.Station;

import java.net.MalformedURLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tin.tothetube.model.utils.BaseUrlUtils.APP_KEY;
import static com.example.tin.tothetube.model.utils.BaseUrlUtils.APP_ID;
import static com.example.tin.tothetube.model.utils.BaseUrlUtils.DEFAULT_LAT;
import static com.example.tin.tothetube.model.utils.BaseUrlUtils.DEFAULT_LON;
import static com.example.tin.tothetube.model.utils.BaseUrlUtils.METRO_STOP_TYPE;
import static com.example.tin.tothetube.model.utils.BaseUrlUtils.RADIUS_METRES;


public class MainPresenter implements MainContract.MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.MainView mainView;

    TflUrlUtils urlUtils;

    MainPresenter(MainContract.MainView view) throws MalformedURLException {
        this.mainView = view;
        urlUtils = BaseUrlUtils.getTflBaseUrl();

    }

    @Override
    public void getAllStations() {
        //view.showLoading();
        urlUtils.getAllStations(METRO_STOP_TYPE, RADIUS_METRES, DEFAULT_LAT, DEFAULT_LON,
                APP_ID, APP_KEY).enqueue(new Callback<Station>() {

            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {

                Log.d(TAG, "getAllStations within 1KM response: " + response.body());
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {

            }
        });
    }
}
