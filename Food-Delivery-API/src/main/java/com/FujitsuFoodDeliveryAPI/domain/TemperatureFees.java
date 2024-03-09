package com.FujitsuFoodDeliveryAPI.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TemperatureFees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    // Default -10
    private double coldTemperatureCeiling;

    @Column(nullable = false)
    // Default 0
    private double lowTemperatureCeiling;
    
    @Column(nullable = false)
    // Default 1
    private double coldTemperatureFee;

    @Column(nullable = false)
    // Default 0.5
    private double lowTemperatureFee;

}
