package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    public WeatherService(ApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherData(String city) {
        String url = String.format("%s/weather?q=%s&appid=%s", apiConfig.getApiUrl(), city, apiConfig.getApiKey());
        return restTemplate.getForObject(url, WeatherData.class);
    }
}
