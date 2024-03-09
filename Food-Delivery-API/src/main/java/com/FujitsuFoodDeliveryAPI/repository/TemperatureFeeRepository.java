package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface TemperatureFeeRepository extends JpaRepository<TemperatureFees, Long> {


    @Query("SELECT tf FROM TemperatureFees tf ORDER BY tf.id DESC LIMIT 1")
    List<TemperatureFees> findLatestTemperatureFees();

    void saveTemperatureFees(double coldTemperatureCeiling, double lowTemperatureCeiling, double coldTemperatureFee, double lowTemperatureFee);

}
