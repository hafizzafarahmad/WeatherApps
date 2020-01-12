package com.example.weatherapps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.weatherapps.Adapter.ForcastAdapter;
import com.example.weatherapps.Model.ForecastResult;
import com.example.weatherapps.Model.WeatherResult;
import com.example.weatherapps.Utils.Common;
import com.example.weatherapps.Utils.IOpenWeatherMap;
import com.example.weatherapps.Utils.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaterWeatherFragment extends Fragment {

    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_later_weather, container, false);
        
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

        recyclerView = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        
        getForcastInformation();

        return view;
    }

    private void getForcastInformation() {
        compositeDisposable.add(mService.getAllWeatherZip("10005", Common.KEY_API, "metric")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ForecastResult>() {
                                @Override
                                public void accept(ForecastResult forecastResults) throws Exception {

                                    recyclerView.setAdapter(new ForcastAdapter(getActivity(), forecastResults));
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.d(String.valueOf(getActivity()), "accept: " + throwable.getMessage());
                                }
                            }));
    }

}
