package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.models.Line;

import java.net.MalformedURLException;
import java.util.ArrayList;


interface DetailContract {

    interface DetailView {

        void showLines(ArrayList<Line> lines);

        void showLoading();

        void hideLoading();
    }

    interface DetailPresenter {

        void getAllLines(Context context, String line) throws MalformedURLException;

    }
}
