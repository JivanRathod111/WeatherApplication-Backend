package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.WeatherAggregationService;
import com.example.demo.Service.WeatherService;
import com.example.demo.entity.WeatherData;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    
    @Autowired
    private WeatherAggregationService weatherAggregationService;


    @GetMapping("/weather")
    public ResponseEntity<String> getWeatherData() {
        JSONObject weatherData = weatherService.getWeatherData();
        
        // Extract relevant data
        JSONObject main = weatherData.getJSONObject("main");
        String weatherMain = weatherData.getJSONArray("weather").getJSONObject(0).getString("main");
        String weatherDescription = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
        
        // Convert temperatures from Kelvin to Celsius
        double feelsLike = main.getDouble("feels_like") - 273.15; // Kelvin to Celsius
        double temp = main.getDouble("temp") - 273.15; // Kelvin to Celsius
        double tempMin = main.getDouble("temp_min") - 273.15; // Minimum temperature
        double tempMax = main.getDouble("temp_max") - 273.15; // Maximum temperature

        // Assuming average temperature can be calculated from current, min, and max values
        double averageTemperature = (temp + tempMin + tempMax) / 3; // Average of current, min, and max

        long dt = weatherData.getLong("dt");

        // Create a JSON response
        JSONObject response = new JSONObject();
        response.put("main", weatherMain);
        response.put("description", weatherDescription);
        response.put("feelsLike", feelsLike);
        response.put("temp", temp);
        response.put("temp_min", tempMin);
        response.put("temp_max", tempMax);
        response.put("averageTemperature", averageTemperature);
        response.put("dominantCondition", weatherDescription); // Set the dominant condition based on description
        response.put("dt", dt);

        return ResponseEntity.ok(response.toString());
    }
//
//    @PostMapping("/weather")
//    public void postWeatherData(@RequestBody Map<String, Object> apiData) {
//        weatherService.saveWeatherDataFromApi(apiData);
//    }
    
    @PostMapping("/weather")
    public ResponseEntity<String> postSpecificWeatherData(@RequestBody Map<String, Object> specificWeatherData) {
        try {
            // Validate the incoming data
            if (specificWeatherData == null || specificWeatherData.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid specific weather data");
            }

            // Create a new WeatherData instance
            WeatherData weatherData = new WeatherData();

            // Extracting properties from the incoming data with null checks
            if (specificWeatherData.get("feelsLike") != null) {
                weatherData.setFeelsLike(((Number) specificWeatherData.get("feelsLike")).doubleValue());
            } else {
                return ResponseEntity.badRequest().body("feelsLike is required");
            }

            if (specificWeatherData.get("temp") != null) {
                weatherData.setTemp(((Number) specificWeatherData.get("temp")).doubleValue());
            } else {
                return ResponseEntity.badRequest().body("temp is required");
            }

            if (specificWeatherData.get("temp_min") != null) {
                weatherData.setTempMin(((Number) specificWeatherData.get("temp_min")).doubleValue());
            } else {
                return ResponseEntity.badRequest().body("temp_min is required");
            }

            if (specificWeatherData.get("temp_max") != null) {
                weatherData.setTempMax(((Number) specificWeatherData.get("temp_max")).doubleValue());
            } else {
                return ResponseEntity.badRequest().body("temp_max is required");
            }

            if (specificWeatherData.get("averageTemperature") != null) {
                weatherData.setAverageTemp(((Number) specificWeatherData.get("averageTemperature")).doubleValue());
            } else {
                return ResponseEntity.badRequest().body("averageTemperature is required");
            }

            if (specificWeatherData.get("main") != null) {
                weatherData.setMain((String) specificWeatherData.get("main"));
            } else {
                return ResponseEntity.badRequest().body("main is required");
            }

            if (specificWeatherData.get("description") != null) {
                weatherData.setDescription((String) specificWeatherData.get("description"));
            } else {
                return ResponseEntity.badRequest().body("description is required");
            }

            if (specificWeatherData.get("dominantCondition") != null) {
                weatherData.setDominantCondition((String) specificWeatherData.get("dominantCondition"));
            } else {
                return ResponseEntity.badRequest().body("dominantCondition is required");
            }

            // Convert Unix timestamp to LocalDateTime
            if (specificWeatherData.get("dt") != null) {
                long unixTimestamp = ((Number) specificWeatherData.get("dt")).longValue();
                LocalDateTime dateTime = LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(unixTimestamp),
                        ZoneId.systemDefault() // Change to UTC or any specific timezone as needed
                );
                weatherData.setDt(dateTime);
            } else {
                return ResponseEntity.badRequest().body("dt is required");
            }

            // Save weather data to repository
            weatherService.saveWeatherData(weatherData); // Assuming you have a method in service for saving WeatherData
            
            return ResponseEntity.ok("Specific weather data saved successfully");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving specific weather data");
        }
    }

    
    
}

