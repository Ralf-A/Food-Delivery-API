# Fujitsu Food Delivery API Documentation

# Description

A sub-functionality of the food delivery application, which
calculates the delivery fee for food couriers based on regional base fee, vehicle type, and weather
conditions. Includes basic tests for creating new weather data and testing the main endpoint of calculateDeliveryFee.

⏰ Calculate delivery base fee based on weather for a current or a specified time. ⏰

⚙️ By default all business logic is same as in task's description, but base fees and extra fees can be managed with REST endpoints below. ⚙️

❗ Exceptions detailed below. ❗

# Starting the Program
## Option 1: Using Docker (Recommended)
- **Step 1**: Run `docker.compose.yml`.
- **Step 2**: Open the application in Docker to see everything working in harmony!
- **Step 3**: By default, app is running on 8080

## Option 2: 
- Running the `Fujitsu-Food-Delivery-API.jar` file

## Option 3: 
- Running `FujitsuFoodApplicationAPI.java` in you IDE

## Delivery Fee Calculation Endpoint

### GET /calculateDeliveryFee
- **Description**: Calculate the delivery fee based on city, vehicle type, and date/time.
- **Parameters**:
   - `city` (String, required): The city for delivery.
   - `vehicle` (String, required): The type of vehicle used for delivery.
   - `dateTime` (LocalDateTime, optional): The date and time for delivery, formatted as `yyyy-MM-dd'T'HH:mm`.
- **Response**: A double value representing the calculated delivery fee.

## Vehicle Type Fees Endpoints

### POST /postVehicleTypeFees
- **Description**: Save base fees for different vehicle types across cities.
- **Parameters**:
  - `tallinnCarBaseFee` (Double, optional): Base fee for cars in Tallinn.
  - `tartuCarBaseFee` (Double, optional): Base fee for cars in Tartu.
  - `parnuCarBaseFee` (Double, optional): Base fee for cars in Parnu.
  - `tallinnScooterBaseFee` (Double, optional): Base fee for scooters in Tallinn.
  - `tartuScooterBaseFee` (Double, optional): Base fee for scooters in Tartu.
  - `parnuScooterBaseFee` (Double, optional): Base fee for scooters in Parnu.
  - `tallinnBikeBaseFee` (Double, optional): Base fee for bikes in Tallinn.
  - `tartuBikeBaseFee` (Double, optional): Base fee for bikes in Tartu.
  - `parnuBikeBaseFee` (Double, optional): Base fee for bikes in Parnu.
- **Response**: `VehicleTypeFees` object with status `201 Created`.

### GET /getVehicleTypeFees
- **Description**: Retrieve the latest vehicle type fees.
- **Response**: `VehicleTypeFees` object.

## Temperature Fees Endpoints

### POST /postTemperatureFees
- **Description**: Save fees based on temperature thresholds.
- **Parameters**:
  - `coldTemperatureCeiling` (Double, optional): Upper limit for cold temperature.
  - `lowTemperatureCeiling` (Double, optional): Upper limit for low temperature.
  - `coldTemperatureFee` (Double, optional): Fee for cold temperature.
  - `lowTemperatureFee` (Double, optional): Fee for low temperature.
- **Response**: `TemperatureFees` object with status `201 Created`.

### GET /getTemperatureFees
- **Description**: Retrieve the latest temperature fees.
- **Response**: `TemperatureFees` object.

## Weather Phenomenon Fees Endpoints

### POST /postWeatherPhenomenonFees
- **Description**: Save fees based on weather phenomena.
- **Parameters**:
  - `heavyWeatherFee` (Double, optional): Fee for heavy weather conditions.
  - `badWeatherFee` (Double, optional): Fee for bad weather conditions.
  - `normalWeatherFee` (Double, optional): Fee for normal weather conditions.
- **Response**: `WeatherPhenomenonFees` object with status `201 Created`.

### GET /getWeatherPhenomenonFees
- **Description**: Retrieve the latest weather phenomenon fees.
- **Response**: `WeatherPhenomenonFees` object.

## Wind Speed Fees Endpoints

### POST /postWindSpeedFees
- **Description**: Save fees based on wind speed thresholds.
- **Parameters**:
  - `windFee` (Double, optional): Fee associated with a specific wind speed.
  - `windFeeFloor` (Double, optional): Lower limit for wind speed to apply the fee.
  - `windFeeCeiling` (Double, optional): Upper limit for wind speed to apply the fee.
- **Response**: `WindSpeedFees` object with status `201 Created`.

### GET /getWindSpeedFees
- **Description**: Retrieve the latest wind speed fees.
- **Response**: `WindSpeedFees` object.

# Exception Handling in Fujitsu Food Delivery API

## Overview
The `GlobalExceptionHandler` class in the Fujitsu Food Delivery API provides centralized exception handling across all `@RequestMapping` methods through `@ExceptionHandler` methods.

## Exceptions

### IncorrectTimeStampException
- **Description**: This exception is thrown when the timestamp provided is incorrect or in an invalid format.
- **Response**: Returns a `400 Bad Request` with an error message detailing the issue.

### InvalidVehicleException
- **Description**: This exception is thrown when the vehicle type provided does not match any known types.
- **Response**: Returns a `400 Bad Request` with an error message detailing the issue.

### InvalidCityException
- **Description**: This exception is thrown when the city provided is not recognized by the system.
- **Response**: Returns a `400 Bad Request` with an error message detailing the issue.

### MissingServletRequestParameterException
- **Description**: This exception is thrown when a required request parameter is missing.
- **Response**: Returns a `400 Bad Request` with a message indicating which parameter is missing.

### MethodArgumentTypeMismatchException
- **Description**: This exception is thrown when a method argument is not the expected type.
- **Response**: Returns a `400 Bad Request` with a message detailing the expected type and the provided value.

### No Handler Found Exception
- **Description**: This exception is thrown when an invalid request is made, for example :8080/fgaskfsa or just :8080/
- **Response**: Returns a `400 Bad Request` with a message with a link leading to this GitHub repository for documentation.
  
### DateTimeParseException Handler
- **Description**: Handles incorrect date and time format inputs.
- **Response**: A `400 Bad Request` status with a message indicating the correct date and time format.


## Usage
These exceptions are used throughout the API to ensure that clients receive informative error messages that can guide them to correct their requests. This approach enhances the robustness and usability of the API.

