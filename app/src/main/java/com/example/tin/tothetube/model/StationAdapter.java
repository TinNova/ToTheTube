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


public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {

    private ArrayList<Station> mStation;
    private Context context;
    /* Initialise The Interface that handles onClicks*/
    private StationPositionListener stationPositionListener;

    /* Constructor:
     * Pass in the StationPositionListener Interface into the Adapter on construction */
    public StationAdapter(ArrayList<Station> station, Context context, StationPositionListener listener) {
        this.mStation = station;
        this.context = context;
        this.stationPositionListener = listener;
    }

    // We are passing the weather data via a method, not when the Adapter is created
    public void setStation(ArrayList<Station> station) {
        this.mStation = station;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public StationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        /*
         * For the onClick's on the Arrival TextViews to work we need to pass in the context
         * provided in the constructor, this context is from the MainActivity
         */
        View v = LayoutInflater.from(context)
                .inflate(R.layout.station_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StationAdapter.ViewHolder viewHolder, int position) {

        Station station = mStation.get(position);

        viewHolder.tvStation.setText(station.getCommonName());
        viewHolder.tvArrivalTime0.setText(String.valueOf
                (station.getArrivals().get(0).getTimeToStation() + " secs"));
        viewHolder.tvArrivalTime1.setText(String.valueOf
                (station.getArrivals().get(1).getTimeToStation() + " secs"));
        viewHolder.tvArrivalTime2.setText(String.valueOf
                (station.getArrivals().get(2).getTimeToStation() + " secs"));

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
        final TextView tvArrivalTime0;
        final TextView tvArrivalTime1;
        final TextView tvArrivalTime2;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStation = itemView.findViewById(R.id.tv_station);
            tvArrivalTime0 = itemView.findViewById(R.id.tv_arrivalTime0);
            tvArrivalTime1 = itemView.findViewById(R.id.tv_arrivalTime1);
            tvArrivalTime2 = itemView.findViewById(R.id.tv_arrivalTime2);

            /* Setting up the onClickListeners */
            tvArrivalTime0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /* Implementing the interface method */
                    stationPositionListener.tvArrivalTime0OnClick(view, getAdapterPosition());
                }
            });

            tvArrivalTime1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stationPositionListener.tvArrivalTime1OnClick(view, getAdapterPosition());
                }
            });

            tvArrivalTime2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stationPositionListener.tvArrivalTime2OnClick(view, getAdapterPosition());
                }
            });
        }
    }
}
