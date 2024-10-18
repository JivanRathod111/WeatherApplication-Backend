package com.example.demo.controller;
package com.example.weather.controller;

import com.example.weather.model.DailySummary;
import com.example.weather.model.Alert;
import com.example.weather.service.WeatherService;
import com.example.weather.service.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final AlertService alertService;

    public WeatherController(WeatherService weatherService, AlertService alertService) {
        this.weatherService = weatherService;
        this.alertService = alertService;
    }

    // Endpoint to get the daily weather summaries
    @GetMapping("/daily-summary")
    public List<DailySummary> getDailyWeatherSummary() {
        return weatherService.getDailySummaries();
    }

    // Endpoint to set alert thresholds
    @PostMapping("/alerts")
    public String setAlert(@RequestBody Alert alert) {
        alertService.addAlert(alert);
        return "Alert configuration saved.";
    }

    // Endpoint to get active alerts
    @GetMapping("/alerts")
    public List<Alert> getAlerts() {
        return alertService.getActiveAlerts();
    }
}

