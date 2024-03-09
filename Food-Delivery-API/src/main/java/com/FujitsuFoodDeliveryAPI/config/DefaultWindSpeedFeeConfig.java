package com.FujitsuFoodDeliveryAPI.config;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import com.FujitsuFoodDeliveryAPI.repository.WindSpeedFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultWindSpeedFeeConfig {

    @Autowired
    private WindSpeedFeeRepository windSpeedFeeRepository;

    @Bean
    public TemperatureFees defaultTemperatureFee() {
        // Check if there's already an entry in the database
        if (windSpeedFeeRepository.count() == 0) {
            WindSpeedFees defaultFee = new WindSpeedFees();
            defaultFee.setWindFee(1);
            defaultFee.setWindFeeCeiling(20);
            defaultFee.setWindFeeFloor(10);
            windSpeedFeeRepository.save(defaultFee);
        }
        return null;
    }
}
