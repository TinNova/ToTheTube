package com.example.tin.tothetube;

import android.content.Context;

import java.net.MalformedURLException;

/**
 * Created by Tin on 30/05/2018.
 */

public interface DetailContract {

    interface DetailView {

    }

    interface DetailPresenter {

        void getLine(Context context, String line) throws MalformedURLException;

    }
}
