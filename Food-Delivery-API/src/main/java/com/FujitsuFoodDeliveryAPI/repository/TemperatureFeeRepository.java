package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface TemperatureFeeRepository extends JpaRepository<TemperatureFees, Long> {
    @Query("SELECT tf FROM TemperatureFees tf ORDER BY tf.id DESC")
    TemperatureFees findLatestTemperatureFees();
}
