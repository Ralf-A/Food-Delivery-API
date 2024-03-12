package com.FujitsuFoodDeliveryAPI.config;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;
import com.FujitsuFoodDeliveryAPI.repository.TemperatureFeeRepository;
import com.FujitsuFoodDeliveryAPI.repository.WeatherPhenomenonFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultWeatherPhenomenonFeeConfig {

    @Autowired
    private WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;
    /**
     * Adds default configuration from task's description for fee calculation for weather phenomenon fees
     */

    @Bean
    public WeatherPhenomenonFees weatherPhenomenonFee() {
        // Check if there's already an entry in the database
        if (weatherPhenomenonFeeRepository.count() == 0) {
            WeatherPhenomenonFees defaultFee = new WeatherPhenomenonFees();
            defaultFee.setHeavyWeatherFee(1.0);
            defaultFee.setBadWeatherFee(0.5);
            defaultFee.setNormalWeatherFee(0);
            weatherPhenomenonFeeRepository.save(defaultFee);
        }
        return null;
    }
}
