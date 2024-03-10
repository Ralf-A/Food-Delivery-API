package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface WindSpeedFeeRepository extends JpaRepository<WindSpeedFees, Long> {
    @Query("SELECT wsf FROM WindSpeedFees wsf ORDER BY wsf.id DESC")
    WindSpeedFees findLatestWindSpeedFees();
}
