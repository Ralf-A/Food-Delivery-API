package com.FujitsuFoodDeliveryAPI.repository;

import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    // Custom query to find the most recent weather data entry for a given WMO code and date, or just by WMO code if no date is provided
    @Query("SELECT wd FROM WeatherData wd WHERE wd.wmoCode = :wmoCode AND (:observationTimestamp IS NULL OR wd.observationTimestamp <= :timestamp) ORDER BY wd.observationTimestamp DESC")
    Optional<WeatherData> findTopByWmoCodeAndObservationTimestampLessThanEqualOrderByObservationTimestampDesc(@Param("wmoCode") String wmoCode, @Param("timestamp") Timestamp observationTimestamp);


    // Custom query to find the most recent weather data entry for a given WMO code
    @Query("SELECT wd FROM WeatherData wd WHERE wd.wmoCode = :wmoCode ORDER BY wd.observationTimestamp DESC")
    Optional<WeatherData> findTopByWmoCodeOrderByObservationTimestampDesc(@Param("wmoCode") String wmoCode);
}
