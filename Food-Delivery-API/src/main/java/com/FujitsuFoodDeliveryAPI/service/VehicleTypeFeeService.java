package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.VehicleTypeFees;

import com.FujitsuFoodDeliveryAPI.repository.VehicleTypeFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeFeeService {
    /**
     * Service class responsible for posting and getting vehicle type fees, depending on city and vehicle type
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleTypeFeeService.class);

    @Autowired
    private VehicleTypeFeeRepository vehicleTypeFeeRepository;

    public VehicleTypeFees saveVehicleTypeFees(Double tallinnCarBaseFee, Double tallinnBikeBaseFee, Double tallinnScooterBaseFee,
                                               Double tartuBikeBaseFee, Double tartuCarBaseFee, Double tartuScooterBaseFee, 
                                               Double pärnuBikeBaseFee, Double pärnuCarBaseFee, Double pärnuScooterBaseFee) {
        LOGGER.info("Initializing change temperature fees");
        VehicleTypeFees vehicleTypeFees = vehicleTypeFeeRepository.findLatestVehicleTypeFees();

        if (vehicleTypeFees == null) {
            vehicleTypeFees = new VehicleTypeFees();
        }
        if (tallinnCarBaseFee != null) {
            vehicleTypeFees.setTallinnCarBaseFee(tallinnCarBaseFee);
        }
        if (tallinnBikeBaseFee != null) {
            vehicleTypeFees.setTallinnBikeBaseFee(tallinnBikeBaseFee);
        }
        if (tallinnScooterBaseFee != null) {
            vehicleTypeFees.setTallinnScooterBaseFee(tallinnScooterBaseFee);
        }
        if (tartuBikeBaseFee != null) {
            vehicleTypeFees.setTartuBikeBaseFee(tartuBikeBaseFee);
        }
        if (tartuCarBaseFee != null) {
            vehicleTypeFees.setTartuCarBaseFee(tartuCarBaseFee);
    }
        if (tartuScooterBaseFee != null) {
            vehicleTypeFees.setTartuScooterBaseFee(tartuScooterBaseFee);
    }
        if (pärnuScooterBaseFee != null) {
            vehicleTypeFees.setPärnuScooterBaseFee(pärnuScooterBaseFee);
    }
        if (pärnuBikeBaseFee != null) {
            vehicleTypeFees.setPärnuBikeBaseFee(pärnuBikeBaseFee);
    }
        if (pärnuCarBaseFee != null) {
            vehicleTypeFees.setPärnuCarBaseFee(pärnuCarBaseFee);
    }
        LOGGER.info("Changing vehicle type fees to: " +
                (tallinnCarBaseFee != null ? tallinnCarBaseFee : "no change") + ", " +
                (tallinnScooterBaseFee != null ? tallinnScooterBaseFee : "no change") + ", " +
                (tallinnBikeBaseFee != null ? tallinnBikeBaseFee : "no change") + ", " +
                (tartuCarBaseFee != null ? tartuCarBaseFee : "no change") + ", " +
                (tartuScooterBaseFee != null ? tartuScooterBaseFee : "no change") + ", " +
                (tartuBikeBaseFee != null ? tartuBikeBaseFee : "no change") + ", " +
                (pärnuCarBaseFee != null ? pärnuCarBaseFee : "no change") + ", " +
                (pärnuScooterBaseFee != null ? pärnuScooterBaseFee : "no change") + ", " +
                (pärnuBikeBaseFee != null ? pärnuBikeBaseFee : "no change"));

        return vehicleTypeFeeRepository.save(vehicleTypeFees);
    }
    
    public VehicleTypeFees findLatestVehicleTypeFees(){
        LOGGER.info("Getting latest vehicle type fees: " + vehicleTypeFeeRepository.findLatestVehicleTypeFees());
        return vehicleTypeFeeRepository.findLatestVehicleTypeFees();
    }
}
