package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    private final WeatherService weatherService;

    public WeatherScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Runs every 5 minutes
    @Scheduled(fixedRate = 300000)
    public void fetchWeatherData() {
        // Fetch weather data for specified cities
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        for (String city : cities) {
            weatherService.getWeatherData(city);
            // Additional processing can be done here
        }
    }
}

