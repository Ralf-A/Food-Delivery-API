package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.VehicleTypeFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface VehicleTypeFeeRepository extends JpaRepository<VehicleTypeFees, Long> {
    @Query("SELECT tf FROM VehicleTypeFees tf ORDER BY tf.id DESC")
    VehicleTypeFees findLatestVehicleTypeFees();
}
