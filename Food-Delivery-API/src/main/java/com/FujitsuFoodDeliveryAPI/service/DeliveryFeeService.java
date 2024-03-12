package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.logic.DeliveryFee;
import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryFeeService {
    /**
     * Service class responsible for calculating the delivery fee by considering various weather conditions.
     */
    @Autowired
    private DeliveryFee deliveryFee;
    @Autowired
    private WeatherDataService weatherDataService;

    /**
     * Calculates the delivery fee based on current or historical weather data.
     *
     * @param city                 the city where the delivery is to be made
     * @param vehicleType          the type of vehicle used for delivery
     * @param observationTimestamp the timestamp for which the weather data is to be fetched; if null, current weather data is used
     * @return the calculated delivery fee
     */
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
