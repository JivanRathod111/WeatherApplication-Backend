package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String conditionTriggered;
    private LocalDateTime alertTime;
    // Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getConditionTriggered() {
		return conditionTriggered;
	}
	public void setConditionTriggered(String conditionTriggered) {
		this.conditionTriggered = conditionTriggered;
	}
	public LocalDateTime getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(LocalDateTime alertTime) {
		this.alertTime = alertTime;
	}
    
}