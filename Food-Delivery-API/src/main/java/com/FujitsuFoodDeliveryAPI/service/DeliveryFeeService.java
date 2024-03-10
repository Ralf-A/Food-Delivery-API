package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.logic.DeliveryFee;
import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryFeeService {
    @Autowired
    private DeliveryFee deliveryFee;
    @Autowired
    private WeatherDataService weatherDataService;

    public double calculateFee(String city, String vehicleType, LocalDateTime observationTimestamp) {
        WeatherData weatherData;

        if (observationTimestamp != null) {
            // Fetch historical weather data for the provided dateTime
            weatherData = weatherDataService.getTimestampWeatherData(city, observationTimestamp);
        } else {
            // Fetch current weather data
            weatherData = weatherDataService.getCurrentWeatherData(city);
        }

        // Extract weather conditions
        double temperature = weatherData.getAirTemperature();
        double windSpeed = weatherData.getWindSpeed();
        String weatherPhenomenon = weatherData.getWeatherPhenomenon();

        // Calculate and return the delivery fee
        return deliveryFee.calculateDeliveryFee(city, vehicleType, temperature, windSpeed, weatherPhenomenon);
    }
}
