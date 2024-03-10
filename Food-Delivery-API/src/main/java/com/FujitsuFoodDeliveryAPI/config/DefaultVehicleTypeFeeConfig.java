package com.FujitsuFoodDeliveryAPI.config;

import com.FujitsuFoodDeliveryAPI.domain.VehicleTypeFees;
import com.FujitsuFoodDeliveryAPI.repository.VehicleTypeFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultVehicleTypeFeeConfig {
    @Autowired
    private VehicleTypeFeeRepository vehicleTypeFeeRepository;

    @Bean
    public VehicleTypeFees defaultVehicleTypeFee() {
        // Check if there's already an entry in the database
        if (vehicleTypeFeeRepository.count() == 0) {
            VehicleTypeFees defaultFee = new VehicleTypeFees();
            defaultFee.setTallinnCarBaseFee(4);
            defaultFee.setTartuCarBaseFee(3.5);
            defaultFee.setPärnuCarBaseFee(3);
            defaultFee.setTallinnScooterBaseFee(3.5);
            defaultFee.setTartuScooterBaseFee(3);
            defaultFee.setPärnuScooterBaseFee(2.5);
            defaultFee.setTallinnBikeBaseFee(3);
            defaultFee.setTartuBikeBaseFee(2.5);
            defaultFee.setPärnuBikeBaseFee(2);
            vehicleTypeFeeRepository.save(defaultFee);
        }
        return null;
    }
}
