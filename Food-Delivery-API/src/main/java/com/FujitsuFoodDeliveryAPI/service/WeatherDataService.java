package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import com.FujitsuFoodDeliveryAPI.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class WeatherDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherDataService.class);

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Transactional
    public void saveWeatherData(WeatherData weatherData) {
        try {
            weatherDataRepository.save(weatherData);
            LOGGER.info(weatherData.toString());
        } catch (Exception e) {
            LOGGER.error("Error saving weather data: {}", e.getMessage());
        }
    }
    public WeatherData getWeatherData(String city, LocalDateTime dateTime) {
        Timestamp timestamp = Timestamp.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        Optional<WeatherData> weatherData;

        if (dateTime != null) {
            weatherData = weatherDataRepository.findTopByStationNameAndObservationTimestampLessThanEqualOrderByObservationTimestampDesc(city, timestamp);
        } else {
            weatherData = weatherDataRepository.findTopByStationNameOrderByObservationTimestampDesc(city);
        }

        return weatherData.orElseThrow(() -> new IllegalArgumentException("No weather data found for the specified parameters."));
    }
}
