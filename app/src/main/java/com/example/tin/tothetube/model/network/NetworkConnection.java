package com.example.tin.tothetube.model.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tin.tothetube.model.models.Arrival;
import com.example.tin.tothetube.model.models.Line;
import com.example.tin.tothetube.model.models.Station;
import com.example.tin.tothetube.model.json.ArrivalJsonUtils;
import com.example.tin.tothetube.model.json.LineJsonUtils;
import com.example.tin.tothetube.model.json.StationJsonUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class NetworkConnection {

    private static final String TAG = NetworkConnection.class.getSimpleName();

    private ArrayList<Station> mStations = new ArrayList<>();
    private ArrayList<Arrival> mArrivals = new ArrayList<>();
    private ArrayList<Line> mLines = new ArrayList<>();


    private static NetworkConnection instance = null;

    // Required for Volley API
    private final RequestQueue mRequestQueue;

    private NetworkConnection(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized NetworkConnection getInstance(Context context) {
        if (null == instance) {
            instance = new NetworkConnection(context);
        }
        return instance;
    }

    public void getStationResponseFromHttpUrl(String url, final NetworkListener.StationsListener listener) {

        /* If the mStation ArrayList contains old data, remove it */
        if (mStations != null) {
            mStations.clear();
        }

        /* Handler for the JSON response when server returns ok */
        final com.android.volley.Response.Listener<String>
                responseListener = new com.android.volley.Response.Listener<String>() {

            /* If response is successful */
            @Override
            public void onResponse(final String response) {

                mStations = StationJsonUtils.parseStationJson(response);
                Log.d(TAG + ": ", "Response : " + response);
                /* Send mStation ArrayList to MainActivity */
                try {
                    listener.getStationArrayList(mStations);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        };

        /* Handler for when the server returns an error response */
        com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d(TAG, "Volley Stations onErrorResponse: " + error);
            }
        };

        /* This is the body of the Request */
        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener) {
        };

        mRequestQueue.add(request);
    }


    public void getArrivalTimesResponse(ArrayList<Station> stations, final NetworkListener.ArrivalsListener listener) {

        /* If the mArrivals ArrayList contains old data, remove it */
        if (mArrivals != null) {
            mArrivals.clear();
        }

        int i;
        for (i = 0; i < stations.size(); i++) {

            final Station mStation = stations.get(i);

            String url = NetworkUtils.getArrivalsUrl(mStation.getNaptanId());

        /* Handler for the JSON response when server returns ok */
            final com.android.volley.Response.Listener<String>
                    responseListener = new com.android.volley.Response.Listener<String>() {

                /* If response is successful */
                @Override
                public void onResponse(final String response) {

                    mArrivals = ArrivalJsonUtils.parseArrivalJson(response);
                    Log.d(TAG + ": ", "Response : " + response);
                /* Send mStation ArrayList to MainActivity */
                    listener.getArrivalsArrayList(mArrivals, mStation);
                }
            };

        /* Handler for when the server returns an error response */
            com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d(TAG, "Volley Arrivals onErrorResponse: " + error);

                }
            };

        /* This is the body of the Request */
            StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener) {
            };

            mRequestQueue.add(request);
        }
    }

    public void getLineResponseFromHttpUrl(String url, final NetworkListener.LinesListener listener) {

        /* If the mStation ArrayList contains old data, remove it */
        if (mLines != null) {
            mLines.clear();
        }

        /* Handler for the JSON response when server returns ok */
        final com.android.volley.Response.Listener<String>
                responseListener = new com.android.volley.Response.Listener<String>() {

            /* If response is successful */
            @Override
            public void onResponse(final String response) {

                mLines = LineJsonUtils.parseLineJson(response);
                Log.d(TAG + ": ", "Response : " + response);
                /* Send mLines ArrayList to DetailPresenter */
                listener.getLinesArrayList(mLines);
            }
        };

        /* Handler for when the server returns an error response */
        com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d(TAG, "Volley Lines onErrorResponse: " + error);
            }
        };

        /* This is the body of the Request */
        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener) {
        };

        mRequestQueue.add(request);
    }
}
