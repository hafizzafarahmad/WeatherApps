package com.example.weatherapps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapps.Model.ForecastResult;
import com.example.weatherapps.Model.WeatherResult;
import com.example.weatherapps.R;
import com.example.weatherapps.Utils.Common;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ForcastAdapter extends RecyclerView.Adapter<ForcastAdapter.ViewHolder> {

    private Context context;
    private ForecastResult resultList;

    public ForcastAdapter(Context context, ForecastResult resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ForcastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.forcast_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForcastAdapter.ViewHolder viewHolder, int i) {

        Picasso.with(context).load("https://openweathermap.org/img/w/"
                + resultList.getList().get(i).getWeather().get(0).getIcon() + ".png"). into(viewHolder.image);
        viewHolder.suhu.setText(new DecimalFormat("##").format(resultList.getList().get(i).getMain().getTemp()) + " °C");
        viewHolder.cuaca.setText(resultList.getList().get(i).getWeather().get(0).getDescription());
        viewHolder.date.setText(Common.convertDateForecast(resultList.getList().get(i).getDt()));

    }

    @Override
    public int getItemCount() {
        return resultList.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cuaca, suhu, date;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuaca = itemView.findViewById(R.id.txt_cuaca);
            suhu = itemView.findViewById(R.id.txt_temperature);
            image = itemView.findViewById(R.id.img_weather);
            date = itemView.findViewById(R.id.txt_date);
        }
    }
}
