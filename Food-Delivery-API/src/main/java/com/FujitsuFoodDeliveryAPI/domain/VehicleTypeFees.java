package com.FujitsuFoodDeliveryAPI.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class VehicleTypeFees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private double tallinnCarBaseFee;
    @Column(nullable = false)
    private double tartuCarBaseFee;
    @Column(nullable = false)
    private double pärnuCarBaseFee;

    @Column(nullable = false)
    private double tallinnScooterBaseFee;
    @Column(nullable = false)
    private double tartuScooterBaseFee;
    @Column(nullable = false)
    private double pärnuScooterBaseFee;

    @Column(nullable = false)
    private double tallinnBikeBaseFee;
    @Column(nullable = false)
    private double tartuBikeBaseFee;
    @Column(nullable = false)
    private double pärnuBikeBaseFee;

}
