package com.FujitsuFoodDeliveryAPI.controller;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.service.TemperatureFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TemperatureFeeController {

    @Autowired
    private TemperatureFeeService temperatureFeeService;

    @GetMapping("/temperatureFee")
    public void postTemperatureFee(
            @RequestParam(required = false) double coldTemperatureCeiling,
            @RequestParam(required = false) double lowTemperatureCeiling,
            @RequestParam(required = false) double coldTemperatureFee,
            @RequestParam(required = false) double lowTemperatureFee
    ) {
        temperatureFeeService.saveTemperatureFees(coldTemperatureCeiling, lowTemperatureCeiling, coldTemperatureFee, lowTemperatureFee);
    }

    @GetMapping("/temperatureFees")
    public List<TemperatureFees> getLatestTemperatureFees() {
        return temperatureFeeService.findLatestTemperatureFees();
    }
}
