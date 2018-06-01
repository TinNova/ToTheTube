package com.example.tin.tothetube.model.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tin.tothetube.R;
import com.example.tin.tothetube.model.models.Line;

import java.util.ArrayList;


public class LineAdapter extends RecyclerView.Adapter<LineAdapter.ViewHolder> {

    private static final String TAG = LineAdapter.class.getSimpleName();

    private ArrayList<Line> mLines;
    private final Context context;
    private final String stationIdClickedOn;

    public LineAdapter(ArrayList<Line> mLines, Context context, String stationIdClickedOn) {
        this.mLines = mLines;
        this.context = context;
        this.stationIdClickedOn = stationIdClickedOn;
    }

    // We are passing the lines data via a method, not when the Adapter is created
    public void setLines(ArrayList<Line> lines) {
        this.mLines = lines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.line_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LineAdapter.ViewHolder viewHolder, int position) {

        Line line = mLines.get(position);

        /* if the current stations ID matches the station clicked on, highlight the station name */
        if ((line.getNaptanId()).equals(stationIdClickedOn)) {

            viewHolder.tvStation.setText(Html.fromHtml("<b>" + line.getCommonName() + "</b>"));
        } else {

            viewHolder.tvStation.setText(line.getCommonName());
        }
    }

    @Override
    public int getItemCount() {
        if (mLines == null) {
            return 0;
        } else {
            return mLines.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvStation;

        ViewHolder(View itemView) {
            super(itemView);

            tvStation = itemView.findViewById(R.id.tv_Station_Detail);

        }
    }
}
