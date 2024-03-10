package com.FujitsuFoodDeliveryAPI.controller;

import com.FujitsuFoodDeliveryAPI.service.DeliveryFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
public class DeliveryFeeController {

    @Autowired
    private DeliveryFeeService deliveryFeeService;

    @GetMapping("/calculateDeliveryFee")
    public double calculateDeliveryFee(
            @RequestParam String city,
            @RequestParam String vehicle,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime) {

        return deliveryFeeService.calculateFee(city, vehicle, dateTime);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(
            DateTimeParseException ex, WebRequest request) {
        // Log the exception details for debugging
        // Return a user-friendly message and a Bad Request status
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid date and time format. Please use the format yyyy-MM-dd'T'HH:mm.");
    }
}
