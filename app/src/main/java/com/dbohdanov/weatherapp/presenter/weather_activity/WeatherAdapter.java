package com.dbohdanov.weatherapp.presenter.weather_activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbohdanov.weatherapp.R;
import com.dbohdanov.weatherapp.presenter.main_activity.PlacesListAdapter;
import com.dbohdanov.weatherapp.repository.data_models.DataWeatherForecast;
import com.google.android.gms.location.places.Place;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * RecycleView adapter for weather foreacast
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private ArrayList<DataWeatherForecast.WeatherItem> weatherItems;
    private Context context;

    public WeatherAdapter(Context context, ArrayList<DataWeatherForecast.WeatherItem> weatherItems) {
        this.context = context;
        this.weatherItems = weatherItems;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_activity_rv_item, parent, false);
        return new WeatherAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        DataWeatherForecast.WeatherItem selectedWeatherItem = weatherItems.get(position);

        Date d = new Date(selectedWeatherItem.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM, dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(d);
        String date = dateFormat.format(d);

        holder.tvDate.setText(date);
        holder.tvTime.setText(time);
        holder.tvTempr.setText(String.valueOf(selectedWeatherItem.getTemperature()));
        holder.tvWeather.setText(selectedWeatherItem.getMain());

        String filePath = "file:///android_asset/weather_icons/"
                + selectedWeatherItem.getIconId().substring(0,2)
                + "d"
                + ".png";
        Picasso.get().load(filePath).into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    public void setItems(ArrayList<DataWeatherForecast.WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvTime;
        TextView tvTempr;
        TextView tvWeather;
        ImageView weatherIcon;


        ViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.weather_item_date);
            tvTime = v.findViewById(R.id.weather_item_time);
            tvWeather = v.findViewById(R.id.weather_item_weather_main);
            tvTempr = v.findViewById(R.id.weather_item_temperature);
            weatherIcon = v.findViewById(R.id.weather_item_ic);
        }
    }
}
