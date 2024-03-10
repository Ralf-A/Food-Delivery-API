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


    /**
     * Calculates the total delivery fee based on the provided parameters
     * @param city              the city where delivery is to be made
     * @param vehicleType       the type of vehicle used
     * @param temperature       the temperature of the area at specified time
     * @param windSpeed         the wind speed of the area at specified time
     * @param weatherPhenomenon the weather phenomenon of the area at specified time
     * @return the total calculated delivery fee
     */
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

    /**
     * Retrieves the base fee for a given city and vehicle type.
     * @param city          the city where the delivery is to be made
     * @param vehicleType   the type of vehicle used for delivery
     * @return the base fee for the specified city and vehicle type
     * @throws InvalidVehicleException if the vehicle type is not recognized
     */
    private double getBaseFee(String city, String vehicleType) {
        VehicleTypeFees vehicleTypeFees = vehicleTypeFeeRepository.findLatestVehicleTypeFees();
        LOGGER.info("Getting base fee for city " + city + " and vehicle " + vehicleType);
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

    /**
     * Calculates the additional fee based on the temperature and vehicle type.
     *
     * @param vehicleType the type of vehicle used for delivery
     * @param temperature the current temperature in the delivery area
     * @return the additional temperature-based fee or 0.0 if no fee applies
     */
    private double getTemperatureFee(String vehicleType, double temperature) {
        if (vehicleType.equals("bike") || vehicleType.equals("scooter")) {
            LOGGER.info("Getting temperature extra fee for temperature of " + temperature + "C and vehicle " + vehicleType);
            TemperatureFees temperatureFees = temperatureFeeRepository.findLatestTemperatureFees();
            if (temperature < temperatureFees.getColdTemperatureCeiling()) {
                return temperatureFees.getColdTemperatureFee();
            } else if (temperature < temperatureFees.getLowTemperatureFee()) {
                return temperatureFees.getLowTemperatureFee();
            }
        }
        return 0.0;
    }

    /**
     * Calculates the additional fee based on the wind speed and vehicle type.
     *
     * @param vehicleType the type of vehicle used for delivery
     * @param windSpeed   the current wind speed in the delivery area
     * @return the additional wind speed-based fee or 0.0 if no fee applies
     * @throws InvalidVehicleException if the vehicle type is not suitable for the current wind conditions
     */
    private double getWindSpeedFee(String vehicleType, double windSpeed) {
        WindSpeedFees windSpeedFees = windSpeedFeeRepository.findLatestWindSpeedFees();
        if ("bike".equals(vehicleType) && windSpeed > windSpeedFees.getWindFeeCeiling()) {
            LOGGER.info("Getting windspeed extra fee for wind speed of " + windSpeed + "m/s and vehicle " + vehicleType);
            throw new InvalidVehicleException("Usage of selected vehicle type is forbidden");
        }
        return windSpeed > windSpeedFees.getWindFeeFloor() ? windSpeedFees.getWindFee() : 0.0;
    }

    /**
     * Calculates the additional fee based on the weather phenomenon and vehicle type.
     *
     * @param vehicleType       the type of vehicle used for delivery
     * @param weatherPhenomenon the current weather phenomenon in the delivery area
     * @return the additional weather phenomenon-based fee or 0.0 if no fee applies
     * @throws InvalidVehicleException if the vehicle type is not suitable for the current weather conditions
     */
    private double getWeatherPhenomenonFee(String vehicleType, String weatherPhenomenon) {
        if ("bike".equals(vehicleType) || ("scooter").equals(vehicleType)){
            LOGGER.info("Getting weather phenomenon extra fee for phenomenon speed of " + weatherPhenomenon + " and vehicle " + vehicleType);
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
