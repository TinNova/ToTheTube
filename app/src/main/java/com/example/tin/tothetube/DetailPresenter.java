package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.NetworkUtils;

import java.net.MalformedURLException;

/**
 * Created by Tin on 30/05/2018.
 */

public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailView detailView;

    DetailPresenter(DetailContract.DetailView view) {
        this.detailView = view;
    }

    @Override
    public void getLine(Context context, String line) throws MalformedURLException {

        String lineUrl = NetworkUtils.getLineUrl(line);

    }
}
