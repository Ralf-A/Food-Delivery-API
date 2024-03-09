package com.FujitsuFoodDeliveryAPI.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class WindSpeedFees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    // Default 0.5
    private double windFee;

    @Column(nullable = false)
    // Default 10
    private double windFeeFloor;

    @Column(nullable = false)
    //Default 20
    private double windFeeCeiling;

}
