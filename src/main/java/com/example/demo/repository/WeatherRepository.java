package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DailySummery;


@Repository
public interface WeatherRepository extends JpaRepository<DailySummery, Long> {
    // Additional query methods can be defined here
}
