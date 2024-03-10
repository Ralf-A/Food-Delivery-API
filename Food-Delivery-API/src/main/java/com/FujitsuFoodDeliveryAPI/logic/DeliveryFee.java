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
        vehicleType = vehicleType.toLowerCase();
        weatherPhenomenon = weatherPhenomenon.toLowerCase();
        double baseFee = getBaseFee(city, vehicleType);
        double temperatureFee = getTemperatureFee(vehicleType, temperature);
        double windSpeedFee = getWindSpeedFee(vehicleType, windSpeed);
        double weatherPhenomenonFee = getWeatherPhenomenonFee(vehicleType, weatherPhenomenon);
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
                    return vehicleTypeFees.getPärnuCarBaseFee();
                } else if ("scooter".equals(vehicleType)) {
                    return vehicleTypeFees.getPärnuScooterBaseFee();
                } else if ("bike".equals(vehicleType)) {
                    return vehicleTypeFees.getPärnuBikeBaseFee();
                } else {
                    throw new InvalidVehicleException("Invalid vehicle type");
                }
            default:
                throw new InvalidCityException("Unknown city");
        }
    }



    private double getTemperatureFee(String vehicleType, double temperature) {
        if (vehicleType.equals("bike") || vehicleType.equals("scooter")) {
            TemperatureFees temperatureFees = temperatureFeeRepository.findLatestTemperatureFees();
            if (temperature < temperatureFees.getColdTemperatureCeiling()) {
                return temperatureFees.getColdTemperatureFee();
            } else if (temperature < temperatureFees.getLowTemperatureFee()) {
                return temperatureFees.getLowTemperatureFee();
            }
        }
        return 0.0;
    }

    private double getWindSpeedFee(String vehicleType, double windSpeed) {
        WindSpeedFees windSpeedFees = windSpeedFeeRepository.findLatestWindSpeedFees();
        if ("bike".equals(vehicleType) && windSpeed > windSpeedFees.getWindFeeCeiling()) {
            throw new InvalidVehicleException("Usage of selected vehicle type is forbidden");
        }
        return windSpeed > windSpeedFees.getWindFeeFloor() ? windSpeedFees.getWindFee() : 0.0;
    }

    private double getWeatherPhenomenonFee(String vehicleType, String weatherPhenomenon) {
        if ("bike".equals(vehicleType) || ("scooter").equals(vehicleType)){
            WeatherPhenomenonFees weatherPhenomenonFees = weatherPhenomenonFeeRepository.findLatestWeatherPhenomenonFees();
            if (weatherPhenomenon.equals("glaze") || weatherPhenomenon.equals("hail") || weatherPhenomenon.equals("thunder")){
                throw new InvalidVehicleException("“Usage of selected vehicle type is forbidden");
            }
            else {
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
        return 0;
    }
}
