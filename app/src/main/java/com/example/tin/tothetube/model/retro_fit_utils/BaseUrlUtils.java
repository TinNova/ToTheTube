package com.example.tin.tothetube.model.retro_fit_utils;

import com.example.tin.tothetube.BuildConfig;

public class BaseUrlUtils {

    private static final String BASE_TFL_URL = "https://api.tfl.gov.uk";

    /* Path for API */
    static final String STOP_POINT_PATH = "StopPoint";
    public static final String ARRIVALS_PATH = "Arrival";

    /* Parameters for API*/
    static final String STOP_TYPES_PARAM = "stopTypes";
    static final String RADIUS_PARAM = "radius";
    static final String LAT_PARAM = "lat";
    static final String LON_PARAM = "lon";
    static final String APP_ID_PARAM = "app_id";
    static final String APP_KEY_PARAM = "app_key";

    /* Parameter Values for API */
    // FOR THE STOPS API: https://api.tfl.gov.uk/swagger/ui/index.html?url=/swagger/docs/v1#!/StopPoint/StopPoint_GetByGeoPoint
    public static final String METRO_STOP_TYPE = "NaptanMetroStation";
    public static final String RADIUS_METRES = "1000";
    public static final String APP_ID = BuildConfig.TFL_APP_ID;
    public static final String APP_KEY = BuildConfig.TFL_APP_KEY;

    /* Default lat/lon refers to Covent Garden, it is used when user is outside of London */
    public static final double DEFAULT_LAT = 51.514;
    public static final double DEFAULT_LON = 0.122;

//    public static TflUrlUtils getTflBaseUrl() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_TFL_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(TflUrlUtils.class);
//    }

}
