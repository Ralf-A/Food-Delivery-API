package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface WindSpeedFeeRepository extends JpaRepository<WindSpeedFees, Long> {
    @Query("SELECT tf FROM WindSpeedFees tf ORDER BY tf.id DESC")
    WindSpeedFees findLatestWindSpeedFees();
}
