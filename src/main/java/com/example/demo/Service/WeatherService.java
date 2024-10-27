package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.WeatherData;
import com.example.demo.repository.WeatherDataRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class WeatherService {
	
	@Autowired
	 private WeatherDataRepository weatherDataRepository;

    private static final String API_KEY = "18437445f3f6d472eb7d513722fbaea7";
    private static final String CITY = "Delhi";

    public JSONObject getWeatherData() {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", CITY, API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return new JSONObject(response);
    }
    
//    public void saveWeatherDataFromApi(Map<String, Object> apiData) {
//        WeatherData weatherData = new WeatherData();
//        
//        // Extract and set temperature, feels_like, and weather main description
//        weatherData.setTemp(((Number) apiData.get("temp")).doubleValue());
//        weatherData.setFeelsLike(((Number) apiData.get("feels_like")).doubleValue());
//        
//        // Extracting the main weather condition
//        String mainWeather = ((JSONArray) apiData.get("weather")).getJSONObject(0).getString("main");
//        weatherData.setMain(mainWeather);
//
//        // Extracting min and max temperature
//        weatherData.setTempMin(((Number) apiData.get("temp_min")).doubleValue());
//        weatherData.setTempMax(((Number) apiData.get("temp_max")).doubleValue());
//
//        // Calculate average temperature
//        double averageTemp = (weatherData.getTemp() + weatherData.getTempMin() + weatherData.getTempMax()) / 3;
//        weatherData.setAverageTemp(averageTemp);
//
//        // Convert Unix timestamp to LocalDateTime
//        long unixTimestamp = ((Number) apiData.get("dt")).longValue();
//        LocalDateTime dateTime = LocalDateTime.ofInstant(
//            Instant.ofEpochSecond(unixTimestamp), 
//            ZoneId.systemDefault() // Change to UTC or any specific timezone as needed
//        );
//        weatherData.setDt(dateTime);
//        
//        // Save weather data to repository
//        weatherDataRepository.save(weatherData);
//    }

    public void saveWeatherData(WeatherData weatherData) {
        if (weatherData != null) {
            weatherDataRepository.save(weatherData);
        } else {
            throw new IllegalArgumentException("Weather data cannot be null");
        }
    }

}

