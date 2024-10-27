package com.example.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DailySummery;
import com.example.demo.entity.WeatherData;
import com.example.demo.repository.DailySummeryRepository;
import com.example.demo.repository.WeatherDataRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherAggregationService {

//	@Autowired
//    private WeatherDataRepository weatherDataRepository;
//
//    @Autowired
//    private DailySummeryRepository dailyWeatherSummaryRepository;
//
//    public void aggregateDailyWeatherData(LocalDate date) {
//        // Fetch all weather data for the given date
//        List<WeatherData> weatherDataList = weatherDataRepository.findByDate(date);
//
//        if (weatherDataList.isEmpty()) {
//            System.out.println("No weather data available for the given date.");
//            return;
//        }
//
//        // Calculate average, max, and min temperatures
//        double averageTemperature = weatherDataList.stream().mapToDouble(WeatherData::getTemp).average().orElse(0.0);
//        double maxTemperature = weatherDataList.stream().mapToDouble(WeatherData::getTemp).max().orElse(0.0);
//        double minTemperature = weatherDataList.stream().mapToDouble(WeatherData::getTemp).min().orElse(0.0);
//
//        // Determine the dominant weather condition
//        Map<String, Long> conditionFrequency = weatherDataList.stream()
//                .collect(Collectors.groupingBy(WeatherData::getMain, Collectors.counting()));
//
//        String dominantCondition = conditionFrequency.entrySet().stream()
//                .max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey)
//                .orElse("Unknown");
//
//        // Create or update the daily weather summary
//        DailySummery summary = dailyWeatherSummaryRepository.findByDate(date)
//                .orElse(new DailySummery());
//        summary.setDate(date);
//        summary.setAverageTemperature(averageTemperature);
//        summary.setMaximumTemperature(maxTemperature);
//        summary.setMinimumTemperature(minTemperature);
//        summary.setDominantCondition(dominantCondition);
//
//        // Save the summary to the database
//        dailyWeatherSummaryRepository.save(summary);
//    }
}
