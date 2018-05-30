package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.Line;
import com.example.tin.tothetube.model.NetworkConnection;
import com.example.tin.tothetube.model.NetworkListener;
import com.example.tin.tothetube.model.NetworkUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Tin on 30/05/2018.
 */

public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailView detailView;

    Context mcontext;

    DetailPresenter(DetailContract.DetailView view) {
        this.detailView = view;
    }

    @Override
    public void getAllLines(Context context, String line) throws MalformedURLException {

        String lineUrl = NetworkUtils.getLineUrl(line);

        mcontext = context;
        /*
         * Use the String URL "lineUrl" to request the JSON from the server
         * and parse it
         */
        NetworkConnection.getInstance(context).getLineResponseFromHttpUrl(lineUrl, new NetworkListener.LinesListener() {
            @Override
            public void getLinesArrayList(ArrayList<Line> lines) {

                /* Send Lines to DetailActivity */
                detailView.showLines(lines);
            }
        });
    }
}
