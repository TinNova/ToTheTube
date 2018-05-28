package com.example.tin.tothetube.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class NetworkConnection {

    private static final String TAG = NetworkConnection.class.getSimpleName();

    private ArrayList<Station> mStation = new ArrayList<>();
    private ArrayList<Arrival> mArrival = new ArrayList<>();


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

    /* This prevents the code from needed to pass the context each time */
    public static synchronized NetworkConnection getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkConnection.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void getStationResponseFromHttpUrl(String url, final NetworkListener.StationsListener listener) throws MalformedURLException {

        /* If the mStation ArrayList contains old data, remove it */
        if (mStation != null) {
            mStation.clear();
        }

        /* Handler for the JSON response when server returns ok */
        final com.android.volley.Response.Listener<String>
                responseListener = new com.android.volley.Response.Listener<String>() {

            /* If response is successful */
            @Override
            public void onResponse(final String response) {

                mStation = StationJsonUtils.parseStationJson(response);
                Log.d(TAG + ": ", "Response : " + response);
                /* Send mStation ArrayList to MainActivity */
                try {
                    listener.getStationArrayList(mStation);
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



    public void getArrivalTimesResponse(ArrayList<Station> stations, final NetworkListener.ArrivalsListener listener) throws MalformedURLException {

        int i;
        for (i = 0; i < stations.size(); i++) {

            final Station mStation = stations.get(i);

            String url = NetworkUtils.getArrivalsUrl(mStation.getNaptanId());

            //TODO: this should be done only when the 0th Arrival Url is passed

//        /* If the mStation ArrayList contains old data, remove it */
//        if (mStation != null) {
//            mStation.clear();
//        }

        /* Handler for the JSON response when server returns ok */
            final com.android.volley.Response.Listener<String>
                    responseListener = new com.android.volley.Response.Listener<String>() {

                /* If response is successful */
                @Override
                public void onResponse(final String response) {

                    mArrival = ArrivalJsonUtils.parseArrivalJson(response);
                    Log.d(TAG + ": ", "Response : " + response);
                /* Send mStation ArrayList to MainActivity */
                    listener.getArrivalsArrayList(mArrival, mStation);
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
}
