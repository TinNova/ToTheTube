package com.example.tin.tothetube.model.utils;

import android.util.Log;

import com.example.tin.tothetube.model.Line;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LineJsonUtils {

    private static final String TAG = LineJsonUtils.class.getSimpleName();

    private static final String TFL_STATION_ID = "naptanId";
    private static final String TFL_STATION_NAME = "commonName";

    public static ArrayList<Line> parseLineJson(String response) {

        ArrayList<Line> mLines = new ArrayList<>();

        try {

        /* Define the "stopPoints" JsonArray as a JSONArray */
            JSONArray tflJsonArray = new JSONArray(response);

        /* Using a for loop to cycle through each JsonObject within the listJsonArray */
            for (int i = 0; i < tflJsonArray.length(); i++) {

            /* Get the ith forecast in the JSON and define it as a JsonObject */
                JSONObject lineJsonObject = tflJsonArray.getJSONObject(i);

                String stationId = lineJsonObject.getString(TFL_STATION_ID);
                String stationName = lineJsonObject.getString(TFL_STATION_NAME);

                Line line = new Line(
                        stationId,
                        stationName
                );

                mLines.add(line);
                Log.d(TAG, "line List: " + line);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Total Lines: " + mLines);
        return mLines;
    }
}
