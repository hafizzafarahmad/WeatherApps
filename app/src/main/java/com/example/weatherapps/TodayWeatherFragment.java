package com.example.weatherapps;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapps.Model.WeatherResult;
import com.example.weatherapps.Utils.Common;
import com.example.weatherapps.Utils.IOpenWeatherMap;
import com.example.weatherapps.Utils.RetrofitClient;
import com.example.weatherapps.Utils.SaveDataPreference;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment {

    ImageView img_weather;
    ImageButton settingbtn;
    TextView txt_nama, txt_city, txt_humidity, txt_sunset, txt_sunrise, txt_temperature,
            txt_pressure, txt_descript, txt_datetime, txt_geo;

    ProgressBar loading;
    LinearLayout weather_panel;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;
    String nama, zip, welcome;
    int time;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

        settingbtn = view.findViewById(R.id.settingbtn);
        txt_nama = view.findViewById(R.id.txt_nama);
        txt_city = view.findViewById(R.id.txt_city);
        txt_humidity = view.findViewById(R.id.txt_humidity);
        txt_sunset = view.findViewById(R.id.txt_sunset);
        txt_sunrise = view.findViewById(R.id.txt_sunrise);
        txt_temperature = view.findViewById(R.id.txt_temperature);
        txt_pressure = view.findViewById(R.id.txt_pressure);
        txt_descript = view.findViewById(R.id.txt_description);
        txt_datetime = view.findViewById(R.id.txt_date_time);
        txt_geo = view.findViewById(R.id.txt_geo_coord);
        img_weather = view.findViewById(R.id.img_weather);

        weather_panel = view.findViewById(R.id.weather_panel);
        loading = view.findViewById(R.id.loading);

        nama = SaveDataPreference.getName(getActivity());
        zip = SaveDataPreference.getZip(getActivity());

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        getWeatherInformtion();

        return view;
    }

    private void getWeatherInformtion() {
        compositeDisposable.add(mService.getWeatherZip(zip, Common.KEY_API, "metric")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<WeatherResult>() {
                                @Override
                                public void accept(WeatherResult weatherResult) throws Exception {
                                    Picasso.with(getActivity()).load("https://openweathermap.org/img/w/"
                                            + weatherResult.getWeather().get(0).getIcon() + ".png").into(img_weather);

                                    time = Integer.parseInt(Common.convertTime(weatherResult.getDt()));
                                    if (time >= 0 && time <= 9){
                                        welcome = "Morning";
                                    }else if (time >= 10 && time <= 15){
                                        welcome = "Afternoon";
                                    }else if (time >= 16 && time <=24){
                                        welcome = "Evening";
                                    }

                                    txt_nama.setText("Good " + welcome + " " + nama);
                                    txt_city.setText(weatherResult.getName());
                                    txt_descript.setText(weatherResult.getWeather().get(0).getDescription());
                                    txt_temperature.setText(new DecimalFormat("##").format(weatherResult.getMain().getTemp())  + "°C");
                                    txt_datetime.setText(Common.convertDate(weatherResult.getDt()));
                                    txt_pressure.setText(weatherResult.getMain().getPressure() + " hpa");
                                    txt_humidity.setText(weatherResult.getMain().getHumidity() + " %");
                                    txt_sunrise.setText(Common.convertHour(weatherResult.getSys().getSunrise()));
                                    txt_sunset.setText(Common.convertHour(weatherResult.getSys().getSunset()));
                                    txt_geo.setText(weatherResult.getCoord().toString());

                                    weather_panel.setVisibility(View.VISIBLE);
                                    loading.setVisibility(View.GONE);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getActivity(), "ZIP Code Not Fount", Toast.LENGTH_LONG).show();
                                    Log.d(String.valueOf(getActivity()), "accept: " + throwable.getMessage());
                                }
                            }));
    }

}
