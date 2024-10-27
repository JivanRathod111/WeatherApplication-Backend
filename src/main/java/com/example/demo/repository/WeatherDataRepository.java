package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {


	
}
