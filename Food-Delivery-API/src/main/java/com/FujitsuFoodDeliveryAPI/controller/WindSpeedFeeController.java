package com.FujitsuFoodDeliveryAPI.controller;


import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;
import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import com.FujitsuFoodDeliveryAPI.service.WeatherPhenomenonFeeService;
import com.FujitsuFoodDeliveryAPI.service.WindSpeedFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WindSpeedFeeController {
    @Autowired
    private WindSpeedFeeService windSpeedFeeService;

    @RequestMapping(value = "/postWindSpeedFees", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WindSpeedFees> postWindSpeedFees(
            @RequestParam(required = false) Double windFee,
            @RequestParam(required = false) Double windFeeFloor,
            @RequestParam(required = false) Double windFeeCeiling
    ){
        WindSpeedFees windSpeedFees = windSpeedFeeService.saveWindSpeedFees(windFee, windFeeFloor, windFeeCeiling);
        return new ResponseEntity<>(windSpeedFees, HttpStatus.CREATED);
    }

    @GetMapping("/getWindSpeedFees")
    public WindSpeedFees getLatestWindSpeedFees(){
        return windSpeedFeeService.findLatestWindSpeedFees();
    }
}
