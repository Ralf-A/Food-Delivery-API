-- Create a table for storing weather data
CREATE TABLE IF NOT EXISTS WEATHER_DATA (
                                            STATION_NAME VARCHAR(255),
                                            WMO_CODE VARCHAR(255),
                                            AIR_TEMPERATURE DECIMAL(5, 2),
                                            WIND_SPEED DECIMAL(5, 2),
                                            WEATHER_PHENOMENON VARCHAR(255),
                                            OBSERVATION_TIMESTAMP TIMESTAMP
);

-- Insert a record into the weather data table
-- Replace the placeholders with actual values before executing
-- INSERT INTO WEATHER_DATA (STATION_NAME, WMO_CODE, AIR_TEMPERATURE, WIND_SPEED, WEATHER_PHENOMENON, OBSERVATION_TIMESTAMP)
-- VALUES ('Station Name', 'WMO Code', 25.75, 10.50, 'Clear Sky', CURRENT_TIMESTAMP);
