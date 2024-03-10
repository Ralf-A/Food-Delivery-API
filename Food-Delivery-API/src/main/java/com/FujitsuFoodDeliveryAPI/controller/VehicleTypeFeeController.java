package com.FujitsuFoodDeliveryAPI.controller;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.VehicleTypeFees;
import com.FujitsuFoodDeliveryAPI.service.TemperatureFeeService;
import com.FujitsuFoodDeliveryAPI.service.VehicleTypeFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleTypeFeeController {
    /**
     * Controller for vehicle type fee - specific API documentation on GitHub page
     */


    @Autowired
    private VehicleTypeFeeService vehicleTypeFeeService;

    @RequestMapping(value = "/postVehicleTypeFees", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<VehicleTypeFees> postVehicleTypeFees(
            @RequestParam(required = false) Double tallinnCarBaseFee,
            @RequestParam(required = false) Double tartuCarBaseFee,
            @RequestParam(required = false) Double parnuCarBaseFee,
            @RequestParam(required = false) Double tallinnScooterBaseFee,
            @RequestParam(required = false) Double tartuScooterBaseFee,
            @RequestParam(required = false) Double parnuScooterBaseFee,
            @RequestParam(required = false) Double tallinnBikeBaseFee,
            @RequestParam(required = false) Double tartuBikeBaseFee,
            @RequestParam(required = false) Double parnuBikeBaseFee
    ) {
        VehicleTypeFees vehicleTypeFees = vehicleTypeFeeService.saveVehicleTypeFees(tallinnCarBaseFee, tallinnBikeBaseFee, tallinnScooterBaseFee, tartuBikeBaseFee,
                tartuCarBaseFee, tartuScooterBaseFee, parnuBikeBaseFee, parnuCarBaseFee, parnuScooterBaseFee);
        return new ResponseEntity<>(vehicleTypeFees, HttpStatus.CREATED);
    }


    @GetMapping("/getVehicleTypeFees")
    public VehicleTypeFees getLatestVehicleTypeFees() {
        return vehicleTypeFeeService.findLatestVehicleTypeFees();
    }
}
