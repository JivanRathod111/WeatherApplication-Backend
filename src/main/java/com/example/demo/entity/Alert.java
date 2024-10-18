package com.example.demo.entity;


public class Alert {
    private String condition;        // Weather condition to trigger alert (e.g., Rain)
    private double thresholdTemperature; // Temperature threshold for triggering the alert

    // Getters and setters
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getThresholdTemperature() {
        return thresholdTemperature;
    }

    public void setThresholdTemperature(double thresholdTemperature) {
        this.thresholdTemperature = thresholdTemperature;
    }
}
