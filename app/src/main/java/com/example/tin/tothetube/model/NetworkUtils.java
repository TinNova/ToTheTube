package com.example.tin.tothetube.model;

import android.net.Uri;
import android.util.Log;

import com.example.tin.tothetube.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tin on 27/05/2018.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    // Station within 1km Radius API:
    // https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanMetroStation&radius=1000&lat=51.514&lon=-0.122&app_id=0c37c519&app_key=89d67eb25bf2a3e6134f63fb82c72d5c

    // Arrival Times to a Station
    // https://api.tfl.gov.uk/StopPoint/940GZZLUCGN/Arrivals
    // "940GZZLUCGN" = Station ID

//    public static final String BASE_TFL_URL = "https://api.tfl.gov.uk";
//
//    /* Path for API */
//    private static final String STOP_POINT_PATH = "StopPoint";
//    private static final String ARRIVALS_PATH = "Arrival";
//
//    /* Parameters for API*/
//    private static final String STOP_TYPES_PARAM = "stopTypes";
//    private static final String RADIUS_PARAM = "radius";
//    private static final String LAT_PARAM = "lat";
//    private static final String LON_PARAM = "lon";
//    private static final String APP_ID_PARAM = "app_id";
//    private static final String APP_KEY_PARAM = "app_key";
//
//    /* Parameter Values for API */
//    // FOR THE STOPS API: https://api.tfl.gov.uk/swagger/ui/index.html?url=/swagger/docs/v1#!/StopPoint/StopPoint_GetByGeoPoint
//    public static final String METRO_STOP_TYPE = "NaptanMetroStation";
//    public static final String RADIUS_METRES = "1000";
//    public static final String APP_ID = BuildConfig.TFL_APP_ID;
//    public static final String APP_KEY = BuildConfig.TFL_APP_KEY;
//
//    /* Default lat/lon refers to Covent Garden, it is used when user is outside of London */
//    public static final double DEFAULT_LAT = 51.514;
//    public static final double DEFAULT_LON = 0.122;

//    public static String getRadiusUrl(double currentLat, double currentLon) {
//
//        return buildRadiusUrl(currentLat, currentLon);
//    }
//
//    public static String getArrivalsUrl(String naptanId){
//
//        return buildArrivalsUrl(naptanId);
//    }
//
//    private static String buildRadiusUrl(double lat, double lon) {
//        Uri radiusUri = Uri.parse(BASE_TFL_URL).buildUpon()
//                .appendPath(STOP_POINT_PATH)
//                .appendQueryParameter(STOP_TYPES_PARAM, METRO_STOP_TYPE)
//                .appendQueryParameter(RADIUS_PARAM, RADIUS_METRES)
//                .appendQueryParameter(LAT_PARAM, String.valueOf(lat))
//                .appendQueryParameter(LON_PARAM, String.valueOf(lon))
//                .appendQueryParameter(APP_ID_PARAM, APP_ID)
//                .appendQueryParameter(APP_KEY_PARAM, APP_KEY)
//                .build();
//
//        try {
//
//            URL tflStopUrl = new URL(radiusUri.toString());
//            Log.v(TAG, "tflStopUrl: " + tflStopUrl);
//            return convertUrlToString(tflStopUrl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static String buildArrivalsUrl(String naptanId) {
//        Uri arrivalsUri = Uri.parse(BASE_TFL_URL).buildUpon()
//                .appendPath(STOP_POINT_PATH)
//                .appendPath(naptanId)
//                .appendPath(ARRIVALS_PATH)
//                .build();
//
//        try {
//
//            URL tflStopUrl = new URL(arrivalsUri.toString());
//            Log.v(TAG, "tflStopUrl: " + tflStopUrl);
//            return convertUrlToString(tflStopUrl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static String convertUrlToString(URL url) throws MalformedURLException {
//
//        return url.toString();
//    }
}