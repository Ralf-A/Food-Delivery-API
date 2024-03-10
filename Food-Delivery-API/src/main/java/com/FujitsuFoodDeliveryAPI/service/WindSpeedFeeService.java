package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import com.FujitsuFoodDeliveryAPI.repository.WindSpeedFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WindSpeedFeeService {
    /**
     * Service class responsible for posting and getting wind speed fees
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WindSpeedFeeService.class);
    @Autowired
    private WindSpeedFeeRepository windSpeedFeeRepository;

    public WindSpeedFees saveWindSpeedFees(Double windFee, Double windFeeFloor, Double windFeeCeiling){
        LOGGER.info("Initializing change wind speed fees");
        WindSpeedFees windSpeedFees = windSpeedFeeRepository.findLatestWindSpeedFees();

        if (windSpeedFees == null) {
            windSpeedFees = new WindSpeedFees();
            // Initialize with default values or other logic as needed
        }

        if (windFee != null) {
            windSpeedFees.setWindFee(windFee);
        }
        if (windFeeFloor != null) {
            windSpeedFees.setWindFeeFloor(windFeeFloor);
        }
        if (windFeeCeiling != null) {
            windSpeedFees.setWindFeeCeiling(windFeeCeiling);
        }
        LOGGER.info("Changing wind speed fees to: " +
                (windFee != null ? windFee : "no change") + ", " +
                (windFeeFloor != null ? windFeeFloor : "no change") + ", " +
                (windFeeCeiling != null ? windFeeCeiling : "no change"));
        return windSpeedFeeRepository.save(windSpeedFees);
    }
    public WindSpeedFees findLatestWindSpeedFees(){
        LOGGER.info("Getting latest wind speed fees" + windSpeedFeeRepository.findLatestWindSpeedFees());
        return windSpeedFeeRepository.findLatestWindSpeedFees();
    }
}
