package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DailySummery;

import java.time.LocalDate;
import java.util.Optional;

public interface DailySummeryRepository extends JpaRepository<DailySummery, Long> {
	  DailySummery findByDate(LocalDate date);
	  
	  DailySummery findByCityAndDate(String city, LocalDate date);
	  
	  
}

