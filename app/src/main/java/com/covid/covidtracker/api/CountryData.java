package com.covid.covidtracker.api;

import com.google.gson.annotations.SerializedName;

public class CountryData {

    @SerializedName("updated")
    private  String updated;
    private  String country;
    private  String cases;
    private  String todayCases;
    private  String deaths;
    private  String todayDeaths;
    private  String recovered;
    private  String todayRecovered;
    private  String active;
    private  String tests;

    public CountryData(String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String tests) {
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.tests = tests;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public String getTests() {
        return tests;
    }
}
