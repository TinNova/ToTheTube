package com.example.tin.tothetube.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tin.tothetube.R;

import java.util.ArrayList;

/**
 * Created by Tin on 27/05/2018.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {

    private ArrayList<Station> mStation;
    private Context context;

    public StationAdapter(ArrayList<Station> station, Context context) {
        this.mStation = station;
        this.context = context;
    }

    // We are passing the weather data via a method, not when the Adapter is created
    public void setStation(ArrayList<Station> station) {
        this.mStation = station;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public StationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Create a new View and inflate the list_item Layout into it
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.station_list_item, viewGroup, false);

        // Return the View we just created
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StationAdapter.ViewHolder viewHolder, int position) {

        Station station = mStation.get(position);

        viewHolder.tvStation.setText(station.getCommonName());

    }

    @Override
    public int getItemCount() {
        if (mStation == null) {
            return 0;
        } else {
            return mStation.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvStation;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStation = itemView.findViewById(R.id.tv_station);
        }
    }
}
