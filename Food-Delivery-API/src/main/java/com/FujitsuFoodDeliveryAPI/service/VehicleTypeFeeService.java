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
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleTypeFeeService.class);

    @Autowired
    private VehicleTypeFeeRepository vehicleTypeFeeRepository;

    public VehicleTypeFees saveVehicleTypeFees(Double tallinnCarBaseFee, Double tallinnBikeBaseFee, Double tallinnScooterBaseFee,
                                               Double tartuBikeBaseFee, Double tartuCarBaseFee, Double tartuScooterBaseFee, 
                                               Double parnuBikeBaseFee, Double parnuCarBaseFee, Double parnuScooterBaseFee) {
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
        if (parnuScooterBaseFee != null) {
            vehicleTypeFees.setParnuScooterBaseFee(parnuScooterBaseFee);
    }
        if (parnuBikeBaseFee != null) {
            vehicleTypeFees.setParnuBikeBaseFee(parnuBikeBaseFee);
    }
        if (parnuCarBaseFee != null) {
            vehicleTypeFees.setParnuCarBaseFee(parnuCarBaseFee);
    }
        LOGGER.info("Changing vehicle type fees to: " +
                (tallinnCarBaseFee != null ? tallinnCarBaseFee : "no change") + ", " +
                (tallinnScooterBaseFee != null ? tallinnScooterBaseFee : "no change") + ", " +
                (tallinnBikeBaseFee != null ? tallinnBikeBaseFee : "no change") + ", " +
                (tartuCarBaseFee != null ? tartuCarBaseFee : "no change") + ", " +
                (tartuScooterBaseFee != null ? tartuScooterBaseFee : "no change") + ", " +
                (tartuBikeBaseFee != null ? tartuBikeBaseFee : "no change") + ", " +
                (parnuCarBaseFee != null ? parnuCarBaseFee : "no change") + ", " +
                (parnuScooterBaseFee != null ? parnuScooterBaseFee : "no change") + ", " +
                (parnuBikeBaseFee != null ? parnuBikeBaseFee : "no change"));

        return vehicleTypeFeeRepository.save(vehicleTypeFees);
    }
    
    public VehicleTypeFees findLatestVehicleTypeFees(){
        LOGGER.info("Getting latest vehicle type fees: " + vehicleTypeFeeRepository.findLatestVehicleTypeFees());
        return vehicleTypeFeeRepository.findLatestVehicleTypeFees();
    }
}
