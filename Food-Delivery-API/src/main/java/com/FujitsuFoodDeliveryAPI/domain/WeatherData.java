package com.FujitsuFoodDeliveryAPI.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String stationName;

    @Column(nullable = false)
    private String wmoCode;

    @Column(nullable = false)
    private double airTemperature;

    @Column(nullable = false)
    private double windSpeed;

    @Column(nullable = true)
    private String weatherPhenomenon;

    @Column(nullable = false)
    private Timestamp observationTimestamp;

}
