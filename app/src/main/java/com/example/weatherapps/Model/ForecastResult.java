package com.example.weatherapps.Model;

import java.util.List;

public class ForecastResult {

    private String cod;
    private double messag;
    private int cnt;
    private List<MyList> list;
    private City city;

    public ForecastResult() {
    }

    public ForecastResult(String cod, double messag, int cnt, List<MyList> list, City city) {
        this.cod = cod;
        this.messag = messag;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessag() {
        return messag;
    }

    public void setMessag(double messag) {
        this.messag = messag;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<MyList> getList() {
        return list;
    }

    public void setList(List<MyList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
