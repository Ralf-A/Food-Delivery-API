package com.FujitsuFoodDeliveryAPI.config;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.repository.TemperatureFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultTemperatureFeeConfig {

    @Autowired
    private TemperatureFeeRepository temperatureFeeRepository;

    @Bean
    public TemperatureFees defaultTemperatureFee() {
        // Check if there's already an entry in the database
        if (temperatureFeeRepository.count() == 0) {
            TemperatureFees defaultFee = new TemperatureFees();
            defaultFee.setColdTemperatureCeiling(-10);
            defaultFee.setLowTemperatureCeiling(0);
            defaultFee.setColdTemperatureFee(1);
            defaultFee.setLowTemperatureFee(0.5);
            temperatureFeeRepository.save(defaultFee);
        }
        return null;
    }
}
