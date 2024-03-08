package com.FujitsuFoodDeliveryAPI.domain;

public class DeliveryFee {

    // Method to calculate the delivery fee based on input parameters
    public double calculateDeliveryFee(String city, String vehicleType, double temperature, double windSpeed, String weatherPhenomenon) {
        double baseFee = getBaseFee(city, vehicleType);
        double temperatureFee = getTemperatureFee(temperature);
        double windSpeedFee = getWindSpeedFee(vehicleType, windSpeed);
        double weatherPhenomenonFee = getWeatherPhenomenonFee(weatherPhenomenon);
        double result = baseFee + temperatureFee + windSpeedFee + weatherPhenomenonFee;
        return result;
    }

    private double getBaseFee(String city, String vehicleType) {
        // Define base fees for each city and vehicle type
        switch (city) {
            case "Tallinn":
                return vehicleType.equals("car") ? 4.0 : vehicleType.equals("scooter") ? 3.5 : 3.0;
            case "Tartu":
                return vehicleType.equals("car") ? 3.5 : vehicleType.equals("scooter") ? 3.0 : 2.5;
            case "Pärnu":
                return vehicleType.equals("car") ? 3.0 : vehicleType.equals("scooter") ? 2.5 : 2.0;
            default:
                throw new IllegalArgumentException("Unknown city");
        }
    }

    private double getTemperatureFee(double temperature) {
        // Extra fee for temperature
        if (temperature < -10.0) {
            return 1.0;
        } else if (temperature < 0.0) {
            return 0.5;
        }
        return 0.0;
    }

    private double getWindSpeedFee(String vehicleType, double windSpeed) {
        // Extra fee for wind speed (only for Bike)
        if ("Bike".equals(vehicleType) && windSpeed > 20.0) {
            throw new IllegalArgumentException("Usage of selected vehicle type is forbidden");
        }
        return windSpeed > 10.0 ? 0.5 : 0.0;
    }

    private double getWeatherPhenomenonFee(String weatherPhenomenon) {
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
                return 1.0;
            case "Light shower":
            case "Moderate shower":
            case "Heavy shower":
            case "Light rain":
            case "Moderate rain":
            case "Heavy rain":
                return 0.5;
            default:
                return 0.0;
        }
    }
}
