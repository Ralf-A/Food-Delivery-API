package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.DeliveryFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryFeeService {

    @Autowired
    private WeatherDataService weatherDataService; // Assuming this service can fetch weather data

    public double calculateFee(String city, String vehicleType, LocalDateTime dateTime) {
        // Fetch weather conditions based on dateTime if provided, otherwise use current conditions
        double temperature = 0.0;
        double windSpeed = 0.0;
        String weatherPhenomenon = "";

        if (dateTime != null) {
            // Fetch historical weather data for the provided dateTime
            // This is a placeholder for the actual logic
            // temperature = ...;
            // windSpeed = ...;
            // weatherPhenomenon = ...;
        } else {
            // Fetch current weather data
            // This is a placeholder for the actual logic
            // temperature = ...;
            // windSpeed = ...;
            // weatherPhenomenon = ...;
        }

        DeliveryFee deliveryFee = new DeliveryFee();
        return deliveryFee.calculateDeliveryFee(city, vehicleType, temperature, windSpeed, weatherPhenomenon);
    }
}
