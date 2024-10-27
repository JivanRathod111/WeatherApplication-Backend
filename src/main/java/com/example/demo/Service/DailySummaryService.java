package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.DailySummery;
import com.example.demo.repository.DailySummeryRepository;

@Service
public class DailySummaryService {

	@Autowired
	DailySummeryRepository dailySummeryRepository;

	public DailySummery saveDailySummary(DailySummery dailySummery) {
		return dailySummeryRepository.save(dailySummery);
	}

	public DailySummery findByDate(LocalDate date) {
		return dailySummeryRepository.findByDate(date);
	}

	public void save(DailySummery dailySummary) {
		dailySummeryRepository.save(dailySummary);
	}

	public DailySummery findByCityAndDate(String city, LocalDate date) {
		return dailySummeryRepository.findByCityAndDate(city, date);
	}

	// Method to update or create a DailySummery

	public DailySummery updateOrCreateDailySummary(String city, LocalDate date, DailySummery dailySummaryDetails) {
		// Check if the daily summary for the city and date exists
		DailySummery existingSummary = findByCityAndDate(city, date);

		if (existingSummary != null) {
			// Update existing daily summary record
			existingSummary.setAverageTemperature(dailySummaryDetails.getAverageTemperature());
			existingSummary.setMaximumTemperature(dailySummaryDetails.getMaximumTemperature());
			existingSummary.setMinimumTemperature(dailySummaryDetails.getMinimumTemperature());
			existingSummary.setDominantCondition(dailySummaryDetails.getDominantCondition());

			return dailySummeryRepository.save(existingSummary); // Save updated record
		} else {
			// If not found, create a new daily summary record
			DailySummery newSummary = new DailySummery();
			newSummary.setCity(city); // Set the city
			newSummary.setDate(date);
			newSummary.setAverageTemperature(dailySummaryDetails.getAverageTemperature());
			newSummary.setMaximumTemperature(dailySummaryDetails.getMaximumTemperature());
			newSummary.setMinimumTemperature(dailySummaryDetails.getMinimumTemperature());
			newSummary.setDominantCondition(dailySummaryDetails.getDominantCondition());

			return dailySummeryRepository.save(newSummary); // Save new record
		}
	}
}
