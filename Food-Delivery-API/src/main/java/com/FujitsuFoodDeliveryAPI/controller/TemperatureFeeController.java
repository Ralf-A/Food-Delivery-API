package com.FujitsuFoodDeliveryAPI.controller;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.service.TemperatureFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemperatureFeeController {
    /**
     * Controller for temperature fee - specific API documentation on GitHub page
     */

    @Autowired
    private TemperatureFeeService temperatureFeeService;

    @RequestMapping(value = "/postTemperatureFees", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<TemperatureFees> postTemperatureFees(
            @RequestParam(required = false) Double coldTemperatureCeiling,
            @RequestParam(required = false) Double lowTemperatureCeiling,
            @RequestParam(required = false) Double coldTemperatureFee,
            @RequestParam(required = false) Double lowTemperatureFee
    ) {
        TemperatureFees temperatureFees = temperatureFeeService.saveTemperatureFees(coldTemperatureCeiling, lowTemperatureCeiling, coldTemperatureFee, lowTemperatureFee);
        return new ResponseEntity<>(temperatureFees, HttpStatus.CREATED);
    }


    @GetMapping("/getTemperatureFees")
    public TemperatureFees getLatestTemperatureFees() {
        return temperatureFeeService.findLatestTemperatureFees();
    }
}
