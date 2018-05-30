package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.Line;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Tin on 30/05/2018.
 */

public interface DetailContract {

    interface DetailView {

        void showLines(ArrayList<Line> lines);
    }

    interface DetailPresenter {

        void getAllLines(Context context, String line) throws MalformedURLException;

    }
}
