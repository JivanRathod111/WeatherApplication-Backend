package com.example.demo.Service;
package com.example.weather.service;

import com.example.weather.model.Alert;
import com.example.weather.model.WeatherData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final List<Alert> alertConfigurations = new ArrayList<>();

    // Add an alert configuration
    public void addAlert(Alert alert) {
        alertConfigurations.add(alert);
    }

    // Check if current weather data exceeds any configured alert thresholds
    public boolean checkForAlerts(WeatherData weatherData) {
        for (Alert alert : alertConfigurations) {
            if (alert.getCondition().equalsIgnoreCase(weatherData.getMain())) {
                if (weatherData.getTemp() > alert.getThresholdTemperature()) {
                    return true;
                }
            }
        }
        return false;
    }

    // Get the list of active alert configurations
    public List<Alert> getActiveAlerts() {
        return new ArrayList<>(alertConfigurations);
    }
}

