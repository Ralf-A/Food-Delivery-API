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

    // Custom query to find the latest weather data entry before a certain timestamp for a given station
    @Query("SELECT wd FROM WeatherData wd WHERE wd.stationName = :stationName AND wd.observationTimestamp <= :timestamp ORDER BY wd.observationTimestamp DESC")
    Optional<WeatherData> findTopByStationNameAndObservationTimestampLessThanEqualOrderByObservationTimestampDesc(@Param("stationName") String stationName, @Param("timestamp") Timestamp timestamp);

    // Custom query to find the most recent weather data entry for a given station
    @Query("SELECT wd FROM WeatherData wd WHERE wd.stationName = :stationName ORDER BY wd.observationTimestamp DESC")
    Optional<WeatherData> findTopByStationNameOrderByObservationTimestampDesc(@Param("stationName") String stationName);
}
