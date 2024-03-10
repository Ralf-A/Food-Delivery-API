package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.repository.TemperatureFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureFeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureFeeService.class);


    @Autowired
    private TemperatureFeeRepository temperatureFeeRepository;

    public TemperatureFees saveTemperatureFees(Double coldTemperatureCeiling, Double lowTemperatureCeiling, Double coldTemperatureFee, Double lowTemperatureFee) {
        LOGGER.info("Initializing change temperature fees");
        TemperatureFees temperatureFees = temperatureFeeRepository.findLatestTemperatureFees();

        if (temperatureFees == null) {
            temperatureFees = new TemperatureFees();
            // Initialize with default values or other logic as needed
        }

        if (coldTemperatureCeiling != null) {
            temperatureFees.setColdTemperatureCeiling(coldTemperatureCeiling);
        }
        if (lowTemperatureCeiling != null) {
            temperatureFees.setLowTemperatureCeiling(lowTemperatureCeiling);
        }
        if (coldTemperatureFee != null) {
            temperatureFees.setColdTemperatureFee(coldTemperatureFee);
        }
        if (lowTemperatureFee != null) {
            temperatureFees.setLowTemperatureFee(lowTemperatureFee);
        }

        LOGGER.info("Changing temperature fees to: " +
                (coldTemperatureCeiling != null ? coldTemperatureCeiling : "no change") + ", " +
                (lowTemperatureCeiling != null ? lowTemperatureCeiling : "no change") + ", " +
                (coldTemperatureFee != null ? coldTemperatureFee : "no change") + ", " +
                (lowTemperatureFee != null ? lowTemperatureFee : "no change"));

        return temperatureFeeRepository.save(temperatureFees);
    }

    public TemperatureFees findLatestTemperatureFees(){
        LOGGER.info("Getting latest temperature fees: " + temperatureFeeRepository.findLatestTemperatureFees());
        return temperatureFeeRepository.findLatestTemperatureFees();
    }

}
