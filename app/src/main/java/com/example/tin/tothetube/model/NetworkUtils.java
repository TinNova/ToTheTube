package com.example.tin.tothetube.model;

import android.net.Uri;
import android.util.Log;

import com.example.tin.tothetube.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;


public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    // Station within 1km Radius API:
    // https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanMetroStation&radius=1000&lat=51.514&lon=-0.122&app_id=0c37c519&app_key=89d67eb25bf2a3e6134f63fb82c72d5c

    // Arrival Times to a Station API:
    // https://api.tfl.gov.uk/StopPoint/940GZZLUCGN/Arrivals
    // "940GZZLUCGN" = Station ID

    // Lines API:
    // https://api.tfl.gov.uk/Line/victoria/StopPoints?tflOperatedNationalRailStationsOnly=false

    public static final String BASE_TFL_URL = "https://api.tfl.gov.uk";

    /* Path for API */
    private static final String STOP_POINT_PATH = "StopPoint";
    private static final String ARRIVALS_PATH = "Arrivals";
    private static final String LINE_PATH = "Line";
    private static final String STOP_POINT_PATHS = "StopPoints";

    /* Parameters for API*/
    private static final String STOP_TYPES_PARAM = "stopTypes";
    private static final String RADIUS_PARAM = "radius";
    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";
    private static final String APP_ID_PARAM = "app_id";
    private static final String APP_KEY_PARAM = "app_key";
    private static final String TFL_NAT_RAIL_PARAM = "tflOperatedNationalRailStationsOnly";

    /* Parameter Values for API */
    // FOR THE STOPS API: https://api.tfl.gov.uk/swagger/ui/index.html?url=/swagger/docs/v1#!/StopPoint/StopPoint_GetByGeoPoint
    public static final String METRO_STOP_TYPE = "NaptanMetroStation";
    public static final String RADIUS_METRES = "1000";
    public static final String FALSE = "false";
    public static final String APP_ID = BuildConfig.TFL_APP_ID;
    public static final String APP_KEY = BuildConfig.TFL_APP_KEY;

    /* Default lat/lon refers to Covent Garden, it is used when user is outside of London */
    public static final double DEFAULT_LAT = 51.514;
    public static final double DEFAULT_LON = -0.122;

    public static String getLineUrl(String line) {

        return buildLineUrl(line);
    }

    public static String getRadiusUrl(double currentLat, double currentLon) {

        return buildRadiusUrl(currentLat, currentLon);
    }

    public static String getArrivalsUrl(String naptanId) {

        return buildArrivalsUrl(naptanId);
    }

    private static String buildRadiusUrl(double lat, double lon) {
        Uri radiusUri = Uri.parse(BASE_TFL_URL).buildUpon()
                .appendPath(STOP_POINT_PATH)
                .appendQueryParameter(STOP_TYPES_PARAM, METRO_STOP_TYPE)
                .appendQueryParameter(RADIUS_PARAM, RADIUS_METRES)
                .appendQueryParameter(LAT_PARAM, String.valueOf(lat))
                .appendQueryParameter(LON_PARAM, String.valueOf(lon))
                .appendQueryParameter(APP_ID_PARAM, APP_ID)
                .appendQueryParameter(APP_KEY_PARAM, APP_KEY)
                .build();

        try {

            URL url = new URL(radiusUri.toString());
            Log.v(TAG, "tflStopUrl: " + url);
            return convertUrlToString(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String buildArrivalsUrl(String naptanId) {
        Uri arrivalsUri = Uri.parse(BASE_TFL_URL).buildUpon()
                .appendPath(STOP_POINT_PATH)
                .appendPath(naptanId)
                .appendPath(ARRIVALS_PATH)
                .build();

        try {

            URL url = new URL(arrivalsUri.toString());
            Log.v(TAG, "arrivalTimeUrl: " + url);
            return convertUrlToString(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertUrlToString(URL url) throws MalformedURLException {

        return url.toString();
    }

    // https://api.tfl.gov.uk/Line/victoria/StopPoints?tflOperatedNationalRailStationsOnly=false
    // /StopPoints?tflOperatedNationalRailStationsOnly=false
    private static String buildLineUrl(String line) {
        Uri lineUri = Uri.parse(BASE_TFL_URL).buildUpon()
                .appendPath(LINE_PATH)
                .appendPath(line)
                .appendPath(STOP_POINT_PATHS)
                .appendQueryParameter(TFL_NAT_RAIL_PARAM, FALSE)
                .build();

        try {

            URL url = new URL(lineUri.toString());
            Log.v(TAG, "lineUrl: " + url);
            return convertUrlToString(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
