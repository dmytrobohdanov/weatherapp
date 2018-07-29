package com.dbohdanov.weatherapp.presenter.main_activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.dbohdanov.weatherapp.R;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.ViewHolder> {
    private ArrayList<Place> places;
    private Context context;

    public PlacesListAdapter(Context context, ArrayList<Place> places) {
        this.context = context;
        this.places = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_place_item, parent, false);
        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place selectedPlace = places.get(position);
        holder.tvPlaceId.setText(selectedPlace.getId());
        holder.tvPlaceName.setText(selectedPlace.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return places.size();
    }

    public void addItem(Place place) {
        places.add(place);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvPlaceId;
        TextView tvPlaceName;

        ViewHolder(View v) {
            super(v);
            tvPlaceId = v.findViewById(R.id.main_activity_rv_place_id);
            tvPlaceName = v.findViewById(R.id.main_activity_rv_place_name);
        }
    }
}