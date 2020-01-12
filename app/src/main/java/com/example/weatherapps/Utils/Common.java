package com.example.weatherapps.Utils;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static final String KEY_API = "7d8a41f5614018c35daa5a258245b1ba";
    public static Location current_location = null;

    public static String convertDate(int dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEE DD MM yyyy");
        String formated = simpleDateFormat.format(date);
        return formated;
    }

    public static String convertDateForecast(int dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm EEE DD");
        String formated = simpleDateFormat.format(date);
        return formated;
    }

    public static String convertHour(int dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String formated = simpleDateFormat.format(date);
        return formated;
    }

    public static String convertTime(int dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        String formated = simpleDateFormat.format(date);
        return formated;
    }
}
