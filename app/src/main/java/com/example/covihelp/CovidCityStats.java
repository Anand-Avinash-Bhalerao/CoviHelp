package com.example.covihelp;

import androidx.core.content.ContextCompat;

public class CovidCityStats {
    private String city;
    private int confirmed;
    private int active;
    private int color = R.color.wheat;
    private int titleColour = R.color.black;
    private int recovered;
    private int ded;

    public int getTitleColour() {
        return titleColour;
    }

    public void setTitleColour(int titleColour) {
        this.titleColour = titleColour;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDed() {
        return ded;
    }

    public void setDed(int ded) {
        this.ded = ded;
    }

    public CovidCityStats() {
        this.city = "";
        this.confirmed = 0;
    }
    public CovidCityStats(int confirmed, String name,int active,int recovered,int ded) {
        this.confirmed = confirmed;
        this.city = name;
        this.active = active;
        this.recovered = recovered;
        this.ded = ded;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int cases) {
        this.confirmed = cases;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
