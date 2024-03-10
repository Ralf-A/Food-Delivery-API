# Fujitsu Food Delivery API Documentation

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
- **Description**: Retrieve the latest fees based on weather phenomena.
- **Response**: `WeatherPhenomenonFees` object.

## Delivery Fee Calculation Endpoint

### GET /calculateDeliveryFee
- **Description**: Calculate the delivery fee based on city, vehicle type, and date/time.
- **Parameters**:
  - `city` (String, required): The city for delivery.
  - `vehicle` (String, required): The type of vehicle used for delivery.
  - `dateTime` (LocalDateTime, optional): The date and time for delivery, formatted as `yyyy-MM-dd'T'HH:mm`.
- **Response**: A double value representing the calculated delivery fee.

## Exception Handlers

### DateTimeParseException Handler
- **Description**: Handles incorrect date and time format inputs.
- **Response**: A `400 Bad Request` status with a message indicating the correct date and time format.
