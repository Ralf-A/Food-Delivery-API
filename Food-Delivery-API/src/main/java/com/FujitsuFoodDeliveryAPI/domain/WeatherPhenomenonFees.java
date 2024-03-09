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
    private double heavyWeatherFee;
    @Column(nullable = false)
    private double badWeatherFee;
    @Column(nullable = false)
    private double normalWeatherFee;
}
