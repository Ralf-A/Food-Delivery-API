package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;

import com.FujitsuFoodDeliveryAPI.repository.WeatherPhenomenonFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherPhenomenonFeeService {
    /**
     * Service class responsible for posting and getting weather phenomenon fees
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherPhenomenonFeeService.class);

    @Autowired
    private WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;

    public WeatherPhenomenonFees saveWeatherPhenomenonFees(Double heavyWeatherFee, Double badWeatherFee, Double normalWeatherFee) {
        LOGGER.info("Initializing change temperature fees");
        WeatherPhenomenonFees weatherPhenomenonFees = weatherPhenomenonFeeRepository.findLatestWeatherPhenomenonFees();

        if (weatherPhenomenonFees == null) {
            weatherPhenomenonFees = new WeatherPhenomenonFees();
        }
        if (heavyWeatherFee != null) {
            weatherPhenomenonFees.setHeavyWeatherFee(heavyWeatherFee);
        }
        if (badWeatherFee != null) {
            weatherPhenomenonFees.setBadWeatherFee(badWeatherFee);
        }
        if (normalWeatherFee != null) {
            weatherPhenomenonFees.setNormalWeatherFee(normalWeatherFee);
        }
        LOGGER.info("Changing temperature fees to: " +
                (heavyWeatherFee != null ? heavyWeatherFee : "no change") + ", " +
                (badWeatherFee != null ? badWeatherFee : "no change") + ", " +
                (normalWeatherFee != null ? normalWeatherFee : "no change"));
        return weatherPhenomenonFeeRepository.save(weatherPhenomenonFees);
    }

    public WeatherPhenomenonFees findLatestWeatherPhenomenonFees(){
        LOGGER.info("Getting latest weather phenomenon fees: " + weatherPhenomenonFeeRepository.findLatestWeatherPhenomenonFees());
        return weatherPhenomenonFeeRepository.findLatestWeatherPhenomenonFees();
    }
}
