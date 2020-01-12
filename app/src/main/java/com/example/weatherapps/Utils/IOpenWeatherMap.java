package com.example.weatherapps.Utils;

import com.example.weatherapps.Model.ForecastResult;
import com.example.weatherapps.Model.WeatherResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {

    @GET("forecast")
    Observable<ForecastResult> getAllWeatherZip(@Query("zip") String zip,
                                                @Query("appid") String appid,
                                                @Query("units") String units);

    @GET("weather")
    Observable<WeatherResult> getWeatherZip(@Query("zip") String zip,
                                            @Query("appid") String appid,
                                            @Query("units") String units);
}