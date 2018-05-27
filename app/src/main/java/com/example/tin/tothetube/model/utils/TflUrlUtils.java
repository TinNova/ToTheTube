package com.example.tin.tothetube.model.utils;

//import com.example.tin.tothetube.model.Station;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.APP_ID_PARAM;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.APP_KEY_PARAM;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.LAT_PARAM;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.LON_PARAM;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.RADIUS_PARAM;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.STOP_POINT_PATH;
//import static com.example.tin.tothetube.model.utils.BaseUrlUtils.STOP_TYPES_PARAM;

/**
 * Created by Tin on 27/05/2018.
 */

public interface TflUrlUtils {

    /**
     * Interface which creates the URLs for:
     * - Getting all stations within 1km Radius "getAllStations"
     * - Getting all of the train arrive times for a particular station "getArrivalTimes"
     * - Getting all of the stations on a particular line "getLineDetails"
     */

    /* BASE_URL: "https://api.tfl.gov.uk" */

    /* getAllStations Url: */
    /* https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanMetroStation&radius=1000&lat=51.514&lon=-0.122&app_id=0c37c519&app_key=89d67eb25bf2a3e6134f63fb82c72d5c */
    /* https://api.tfl.gov.uk/StopPoint?stopTypes={QUERY}&radius={QUERY}&lat={QUERY}&lon={QUERY}&app_id={QUERY}&app_key={QUERY} */

//    @GET(STOP_POINT_PATH)
//    Call<ArrayList<Station>> getAllStations(
//            @Query(STOP_TYPES_PARAM) String stopType,
//            @Query(RADIUS_PARAM) String radiusValue,
//            @Query(LAT_PARAM) double latValue,
//            @Query(LON_PARAM) double lonValue,
//            @Query(APP_ID_PARAM) String appIdValue,
//            @Query(APP_KEY_PARAM) String appKeyValue);
}
