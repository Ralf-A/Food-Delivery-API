package com.FujitsuFoodDeliveryAPI.controller;

import com.FujitsuFoodDeliveryAPI.service.DeliveryFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DeliveryFeeController {

    @Autowired
    private DeliveryFeeService deliveryFeeService;

    @GetMapping("/calculateDeliveryFee")
    public double calculateDeliveryFee(
            @RequestParam String city,
            @RequestParam String vehicle,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime ) {

        return deliveryFeeService.calculateFee(city, vehicle, dateTime);
    }
}
