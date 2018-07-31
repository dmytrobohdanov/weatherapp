package com.dbohdanov.weatherapp.presenter.main_activity;

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
    private OnItemClickListener onClickListener;

    public PlacesListAdapter(ArrayList<PlaceData> places, OnItemClickListener onClickListener) {
        this.places = places;
        this.onClickListener = onClickListener;
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
        holder.layout.setOnClickListener(v -> onClickListener.onItemClick(places.get(position)));
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


    public interface OnItemClickListener {
        void onItemClick(PlaceData placeData);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tvPlaceName;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.main_activity_rv_place_layout);
            tvPlaceName = v.findViewById(R.id.main_activity_rv_place_name);
        }
    }
}