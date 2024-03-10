package com.FujitsuFoodDeliveryAPI.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class WeatherPhenomenonFees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    // default 1.0
    private double heavyWeatherFee;
    @Column(nullable = false)
    // default 0.5
    private double badWeatherFee;
    @Column(nullable = false)
    // default 0
    private double normalWeatherFee;
}
