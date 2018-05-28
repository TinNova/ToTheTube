package com.example.tin.tothetube.model;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArrivalJsonUtils {

    private static final String TAG = ArrivalJsonUtils.class.getSimpleName();

    private static final String TFL_LINE_ID = "lineId";
    private static final String TFL_LINE_NAME = "lineName";
    private static final String TFL_TIME_TO_STATION = "timeToStation";

    private static final String TFL_JSON_ARRAY = "json";

    public static ArrayList<Arrival> parseArrivalJson(String response) {

        ArrayList<Arrival> mArrival = new ArrayList<>();

        try {

//        /* Define the entire response as a JSON Object */
//            JSONObject tflJsonObject = new JSONObject(response);

        /* Define the "stopPoints" JsonArray as a JSONArray */
            JSONArray tflJsonArray = new JSONArray(response);

        /* Using a for loop to cycle through each JsonObject within the listJsonArray */
            for (int i = 0; i < tflJsonArray.length(); i++) {

            /* Get the ith forecast in the JSON and define it as a JsonObject */
                JSONObject arrivalJsonObject = tflJsonArray.getJSONObject(i);

                String lineId = arrivalJsonObject.getString(TFL_LINE_ID);
                String lineName = arrivalJsonObject.getString(TFL_LINE_NAME);
                long timeToStation = arrivalJsonObject.getLong(TFL_TIME_TO_STATION);


                Arrival arrival = new Arrival(
                        lineId,
                        lineName,
                        timeToStation
                );

                mArrival.add(arrival);
                Log.d(TAG, "Arrival List: " + arrival);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mArrival;
    }

}

