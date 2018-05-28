package com.example.tin.tothetube.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public class StationJsonUtils {

    private static final String TAG = StationJsonUtils.class.getSimpleName();

    private static final String TFL_STOP_POINTS_LIST = "stopPoints";
    private static final String TFL_NAPTAN_ID = "naptanId";
    private static final String TFL_COMMON_NAME = "commonName";
    private static final String TFL_DISTANCE = "distance";
    private static final String TFL_LAT = "lat";
    private static final String TFL_LON = "lon";

    public static ArrayList<Station> parseStationJson(String response) {

        ArrayList<Station> mStation = new ArrayList<>();

        try {

            /* Define the entire response as a JSON Object */
            JSONObject tflJsonObject = new JSONObject(response);

            /* Define the "stopPoints" JsonArray as a JSONArray */
            JSONArray stopPointsJsonArray = tflJsonObject.getJSONArray(TFL_STOP_POINTS_LIST);

            /* Using a for loop to cycle through each JsonObject within the listJsonArray */
            for (int i = 0; i < stopPointsJsonArray.length(); i++) {

                /* Get the ith forecast in the JSON and define it as a JsonObject */
                JSONObject stationJsonObject = stopPointsJsonArray.getJSONObject(i);

                String naptanId = stationJsonObject.getString(TFL_NAPTAN_ID);
                String commonName = stationJsonObject.getString(TFL_COMMON_NAME);
                double distance = stationJsonObject.getDouble(TFL_DISTANCE);
                double lat = stationJsonObject.getDouble(TFL_LAT);
                double lon = stationJsonObject.getDouble(TFL_LON);
                long timeOfArrival0 = 0;
                long timeOfArrival1 = 0;
                long timeOfArrival2 = 0;

                Station station = new Station(
                        naptanId,
                        commonName,
                        distance,
                        lat,
                        lon,
                        timeOfArrival0,
                        timeOfArrival1,
                        timeOfArrival2
                );

                mStation.add(station);
                Log.d(TAG, "Station List: " + station);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mStation;
    }
}
