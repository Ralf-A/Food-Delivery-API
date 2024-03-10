package com.FujitsuFoodDeliveryAPI.logic;

import com.FujitsuFoodDeliveryAPI.domain.TemperatureFees;
import com.FujitsuFoodDeliveryAPI.domain.VehicleTypeFees;
import com.FujitsuFoodDeliveryAPI.domain.WeatherPhenomenonFees;
import com.FujitsuFoodDeliveryAPI.domain.WindSpeedFees;
import com.FujitsuFoodDeliveryAPI.exception.InvalidCityException;
import com.FujitsuFoodDeliveryAPI.exception.InvalidVehicleException;

import com.FujitsuFoodDeliveryAPI.repository.TemperatureFeeRepository;
import com.FujitsuFoodDeliveryAPI.repository.VehicleTypeFeeRepository;
import com.FujitsuFoodDeliveryAPI.repository.WeatherPhenomenonFeeRepository;
import com.FujitsuFoodDeliveryAPI.repository.WindSpeedFeeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DeliveryFee {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryFee.class);

    @Autowired
    private VehicleTypeFeeRepository vehicleTypeFeeRepository;
    @Autowired
    private WeatherPhenomenonFeeRepository weatherPhenomenonFeeRepository;
    @Autowired
    private WindSpeedFeeRepository windSpeedFeeRepository;
    @Autowired
    private TemperatureFeeRepository temperatureFeeRepository;


    // Method to calculate the delivery fee based on input parameters

    public double calculateDeliveryFee(String city, String vehicleType, double temperature, double windSpeed, String weatherPhenomenon) {
        double baseFee = getBaseFee(city, vehicleType);
        double temperatureFee = getTemperatureFee(temperature);
        double windSpeedFee = getWindSpeedFee(vehicleType, windSpeed);
        double weatherPhenomenonFee = getWeatherPhenomenonFee(weatherPhenomenon);
        double result = baseFee + temperatureFee + windSpeedFee + weatherPhenomenonFee;
        LOGGER.info("Getting fee for city: " + city + ", vehicle: " + vehicleType + ", for temperature: " + temperature + ", for windSpeed: " +
                windSpeed + ", for weatherphenomenon: " + weatherPhenomenon + ", TOTAL FEE: " + result);
        return result;
    }

    private double getBaseFee(String city, String vehicleType) {
        VehicleTypeFees vehicleTypeFees = vehicleTypeFeeRepository.findLatestVehicleTypeFees();
        switch (city) {
            case "Tallinn":
                if ("car".equals(vehicleType)) {
                    return vehicleTypeFees.getTallinnCarBaseFee();
                } else if ("scooter".equals(vehicleType)) {
                    return vehicleTypeFees.getTallinnScooterBaseFee();
                } else if ("bike".equals(vehicleType)) {
                    return vehicleTypeFees.getTallinnBikeBaseFee();
                } else {
                    throw new InvalidVehicleException("Invalid vehicle type");
                }
            case "Tartu":
                if ("car".equals(vehicleType)) {
                    return vehicleTypeFees.getTartuCarBaseFee();
                } else if ("scooter".equals(vehicleType)) {
                    return vehicleTypeFees.getTartuScooterBaseFee();
                } else if ("bike".equals(vehicleType)) {
                    return vehicleTypeFees.getTartuBikeBaseFee();
                } else {
                    throw new InvalidVehicleException("Invalid vehicle type");
                }
            case "Pärnu":
                if ("car".equals(vehicleType)) {
                    return vehicleTypeFees.getParnuCarBaseFee();
                } else if ("scooter".equals(vehicleType)) {
                    return vehicleTypeFees.getParnuScooterBaseFee();
                } else if ("bike".equals(vehicleType)) {
                    return vehicleTypeFees.getParnuBikeBaseFee();
                } else {
                    throw new InvalidVehicleException("Invalid vehicle type");
                }
            default:
                throw new InvalidCityException("Unknown city");
        }
    }



    private double getTemperatureFee(double temperature) {
        TemperatureFees temperatureFees = temperatureFeeRepository.findLatestTemperatureFees();
        // Extra fee for temperature
        if (temperature < temperatureFees.getColdTemperatureCeiling()) {
            return temperatureFees.getColdTemperatureFee();
        } else if (temperature < temperatureFees.getLowTemperatureFee()) {
            return temperatureFees.getLowTemperatureFee();
        }
        return 0.0;
    }

    private double getWindSpeedFee(String vehicleType, double windSpeed) {
        WindSpeedFees windSpeedFees = windSpeedFeeRepository.findLatestWindSpeedFees();
        // Extra fee for wind speed (only for Bike)
        if ("Bike".equals(vehicleType) && windSpeed > windSpeedFees.getWindFeeCeiling()) {
            throw new InvalidVehicleException("Usage of selected vehicle type is forbidden");
        }
        return windSpeed > windSpeedFees.getWindFeeFloor() ? windSpeedFees.getWindFee() : 0.0;
    }

    private double getWeatherPhenomenonFee(String weatherPhenomenon) {
        WeatherPhenomenonFees weatherPhenomenonFees = weatherPhenomenonFeeRepository.findLatestWeatherPhenomenonFees();
        // Extra fee for weather phenomenon
        switch (weatherPhenomenon) {
            case "Heavy snow shower":
            case "Light snow shower":
            case "Moderate snow shower":
            case "Light sleet":
            case "Moderate sleet":
            case "Light snowfall":
            case "Moderate snowfall":
            case "Heavy snowfall":
            case "Blowing snow":
            case "Drifting snow":
                return weatherPhenomenonFees.getHeavyWeatherFee();
            case "Light shower":
            case "Moderate shower":
            case "Heavy shower":
            case "Light rain":
            case "Moderate rain":
            case "Heavy rain":
                return weatherPhenomenonFees.getBadWeatherFee();
            default:
                return weatherPhenomenonFees.getNormalWeatherFee();
        }
    }
}
