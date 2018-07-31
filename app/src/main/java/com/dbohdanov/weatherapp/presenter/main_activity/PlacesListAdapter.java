package com.dbohdanov.weatherapp.presenter.main_activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.repository.local_storage.room_files.PlaceData;

import java.util.ArrayList;
import java.util.List;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.ViewHolder> {
    private ArrayList<PlaceData> places;
    private Context context;

    public PlacesListAdapter(Context context, ArrayList<PlaceData> places) {
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
        PlaceData selectedPlace = places.get(position);
        holder.tvPlaceName.setText(selectedPlace.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return places.size();
    }

    public void addItem(PlaceData place) {
        places.add(place);
        notifyDataSetChanged();
    }

    public void setItems(List<PlaceData> placeDataList) {
        places = new ArrayList<>(placeDataList);
        notifyDataSetChanged();
    }

    public PlaceData getItem(int index) {
        return places.get(index);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvPlaceName;

        ViewHolder(View v) {
            super(v);
            tvPlaceName = v.findViewById(R.id.main_activity_rv_place_name);
        }
    }
}