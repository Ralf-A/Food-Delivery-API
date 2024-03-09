package com.FujitsuFoodDeliveryAPI.service;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import com.FujitsuFoodDeliveryAPI.repository.TemperatureFeeRepository;
import com.FujitsuFoodDeliveryAPI.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureFeeService {

    @Autowired
    private TemperatureFeeRepository temperatureFeeRepository;

    public void saveTemperatureFees(double coldTemperatureCeiling, double lowTemperatureCeiling, double coldTemperatureFee, double lowTemperatureFee) {
        TemperatureFees temperatureFees = new TemperatureFees();
        // Set the weather data fields based on the provided parameters
        temperatureFees.setColdTemperatureCeiling(coldTemperatureCeiling);
        temperatureFees.setLowTemperatureCeiling(lowTemperatureCeiling);
        temperatureFees.setColdTemperatureFee(coldTemperatureFee);
        temperatureFees.setLowTemperatureFee(lowTemperatureFee);
        // Save the weather data to the database
        temperatureFeeRepository.saveTemperatureFees(coldTemperatureCeiling, lowTemperatureCeiling, coldTemperatureFee, lowTemperatureFee);
    }
    public List<TemperatureFees> findLatestTemperatureFees(){
        return temperatureFeeRepository.findLatestTemperatureFees();
    }

}
