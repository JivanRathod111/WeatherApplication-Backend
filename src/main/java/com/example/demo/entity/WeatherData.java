package com.example.demo.entity;
package com.example.weather.model;

public class WeatherData {
    private String main;      // Main weather condition
    private double temp;      // Current temperature
    private double feelsLike; // Perceived temperature
    private long dt;          // Timestamp

    // Getters and setters
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}

