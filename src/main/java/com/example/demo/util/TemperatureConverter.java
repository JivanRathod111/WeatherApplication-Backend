package com.example.demo.util;


public class TemperatureConverter {

    // Converts temperature from Kelvin to Celsius
    public static double toCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    // Converts temperature from Kelvin to Fahrenheit
    public static double toFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }
}
