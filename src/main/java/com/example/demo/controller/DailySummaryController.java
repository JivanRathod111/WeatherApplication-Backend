package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.DailySummaryService;
import com.example.demo.entity.DailySummery;
import com.example.demo.repository.DailySummeryRepository;

@RestController
public class DailySummaryController {

	@Autowired
	DailySummaryService dailySummaryService;

	@Autowired
	DailySummeryRepository dailySummeryRepository;

	@PostMapping("/dailySummary")
	public DailySummery addDailySummary(@RequestBody DailySummery dailySummery) {
		return dailySummaryService.saveDailySummary(dailySummery);
	}

	@GetMapping("/dailySummary")
	public ResponseEntity<DailySummery> getDailySummaryByCityAndDate(@RequestParam("city") String city,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		DailySummery summary = dailySummeryRepository.findByCityAndDate(city, date);

		// Check if summary is null, not empty
		if (summary == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

	@PutMapping("/{city}/{date}")
    public ResponseEntity<DailySummery> updateOrCreateDailySummary(
            @PathVariable("city") String city,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody DailySummery dailySummaryDetails) {

        DailySummery updatedSummary = dailySummaryService.updateOrCreateDailySummary(city, date, dailySummaryDetails);

        // Return response based on whether a new record was created or an existing one was updated
        if (updatedSummary != null) {
            return ResponseEntity.ok(updatedSummary);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedSummary); // Created
        }
    }
}