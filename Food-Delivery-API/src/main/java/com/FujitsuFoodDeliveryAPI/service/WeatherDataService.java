package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import com.FujitsuFoodDeliveryAPI.exception.IncorrectTimeStampException;
import com.FujitsuFoodDeliveryAPI.exception.InvalidCityException;
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
    private String getCityCode(String city) {
        switch (city.toLowerCase()) {
            case "tallinn":
                return "26038";
            case "tartu":
                return "26242";
            case "pärnu":
                return "41803";
            default:
                return city;
        }
    }
    public Timestamp roundToNearestMinute(LocalDateTime dateTime) {
        int second = dateTime.getSecond();
        int roundedSecond = (second >= 30) ? 60 : 0;
        dateTime = dateTime.withSecond(0).withNano(0);

        // If rounding seconds exceeds 60, increment the minute
        if (roundedSecond >= 60) {
            dateTime = dateTime.plusMinutes(1).withSecond(0);
        }

        return Timestamp.valueOf(dateTime);
    }

    public WeatherData getTimestampWeatherData(String city, LocalDateTime dateTime) {
        String cityCode = getCityCode(city);
        Optional<WeatherData> weatherData;

        if (dateTime != null) {
            try {
                Timestamp roundedTimestamp = roundToNearestMinute(dateTime);
                LOGGER.info("Fetching weather data for city code: " + cityCode + " at timestamp: " + roundedTimestamp);
                weatherData = weatherDataRepository.findByWmoCode_Timestamp(cityCode, roundedTimestamp);
            } catch (Exception e) {
                throw new IncorrectTimeStampException("Incorrect time format");
            }
            return weatherData.orElseThrow(() -> new IncorrectTimeStampException("No weather data found for this timestamp: " + dateTime));
        } else {
            LOGGER.info("Fetching weather data for city code: " + cityCode + " for the most recent timestamp");
            weatherData = weatherDataRepository.findByWmoCode(cityCode);
            return weatherData.orElseThrow(() -> new IncorrectTimeStampException("No weather data found for the most recent timestamp, " +
                    "usage - yyyy-mm-ddThh:mm"));
        }
    }


    public WeatherData getCurrentWeatherData(String city) {
        String cityCode = getCityCode(city);
        LOGGER.info("Fetching weather data for city code: " + cityCode + " for most recent timestamp");
        Optional<WeatherData> weatherData = weatherDataRepository.findByWmoCode(cityCode);
        return weatherData.orElseThrow(() -> new InvalidCityException("No current weather data found for the specified city."));
    }
}
