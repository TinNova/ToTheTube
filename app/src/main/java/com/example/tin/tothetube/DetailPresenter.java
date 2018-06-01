package com.example.tin.tothetube;

import android.content.Context;

import com.example.tin.tothetube.model.models.Line;
import com.example.tin.tothetube.model.network.NetworkConnection;
import com.example.tin.tothetube.model.network.NetworkListener;
import com.example.tin.tothetube.model.network.NetworkUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailContract.DetailView detailView;

    private Context mcontext;

    DetailPresenter(DetailContract.DetailView view) {
        this.detailView = view;
    }

    @Override
    public void getAllLines(Context context, String line) throws MalformedURLException {

        String lineUrl = NetworkUtils.getLineUrl(line);

        mcontext = context;

        detailView.showLoading();
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
