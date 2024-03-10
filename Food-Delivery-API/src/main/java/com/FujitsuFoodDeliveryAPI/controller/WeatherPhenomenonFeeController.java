package com.FujitsuFoodDeliveryAPI.controller;


import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;
import com.FujitsuFoodDeliveryAPI.service.WeatherPhenomenonFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherPhenomenonFeeController {
    /**
     * Controller for weather phenomenon fee - specific API documentation on GitHub page
     */

    @Autowired
    private WeatherPhenomenonFeeService weatherPhenomenonService;

    @RequestMapping(value = "/postWeatherPhenomenonFees", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WeatherPhenomenonFees> postWeatherPhenomenonFees(
            @RequestParam(required = false) Double heavyWeatherFee,
            @RequestParam(required = false) Double badWeatherFee,
            @RequestParam(required = false) Double normalWeatherFee
    ){
        WeatherPhenomenonFees weatherPhenomenonFees = weatherPhenomenonService.saveWeatherPhenomenonFees(heavyWeatherFee, badWeatherFee, normalWeatherFee);
        return new ResponseEntity<>(weatherPhenomenonFees, HttpStatus.CREATED);
    }

    @GetMapping("getWeatherPhenomenonFees")
    public WeatherPhenomenonFees getLatestWeatherPhenomenonFees(){
        return weatherPhenomenonService.findLatestWeatherPhenomenonFees();
    }
}
