package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherPhenomenonFeeRepository extends JpaRepository<WeatherPhenomenonFees, Long> {
    @Query("SELECT tf FROM WeatherPhenomenonFees tf ORDER BY tf.id DESC")
    WeatherPhenomenonFees findLatestWeatherPhenomenonFees();
}
