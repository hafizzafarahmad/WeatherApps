package com.example.weatherapps.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveDataPreference {

    private static final String PREF_NAME= "name";
    private static final String PREF_ZIP= "zip";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    //Menghapus data
    public static void clearAllPref(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    //Untuk Menyimpan data
    public static void setName(Context ctx, String name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_NAME, name);
        editor.commit();
    }

    public static void setZip(Context ctx, String zip)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_ZIP, zip);
        editor.commit();
    }



    //Untuk mengambil data
    public static String getName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_NAME, "");
    }

    public static String getZip(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_ZIP, "");
    }


}
