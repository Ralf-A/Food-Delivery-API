package com.FujitsuFoodDeliveryAPI.Food.Delivery.API;

import com.FujitsuFoodDeliveryAPI.domain.WeatherData;
import com.FujitsuFoodDeliveryAPI.repository.WeatherDataRepository;
import com.FujitsuFoodDeliveryAPI.service.DeliveryFeeService;
import com.FujitsuFoodDeliveryAPI.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WeatherDataServiceTests {

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @InjectMocks
    private WeatherDataService weatherDataService;
    @InjectMocks
    private DeliveryFeeService deliveryFeeService;

    // Declare three WeatherData objects as fields of the class
    private WeatherData weatherData1;
    private WeatherData weatherData2;
    private WeatherData weatherData3;

    @BeforeEach
    void setUp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        // Initialize each WeatherData object separately and set their properties accordingly
        weatherData1 = new WeatherData();
        weatherData1.setStationName("Test Station Tallinn");
        weatherData1.setWmoCode("26038");
        weatherData1.setAirTemperature(20.5);
        weatherData1.setWindSpeed(5.2);
        weatherData1.setObservationTimestamp(timestamp);

        weatherData2 = new WeatherData();
        weatherData2.setStationName("Test Station Pärnu Bad Weather");
        weatherData2.setWmoCode("41803");
        weatherData2.setAirTemperature(-10.5);
        weatherData2.setWindSpeed(10.2);
        weatherData2.setWeatherPhenomenon("Light shower");
        weatherData2.setObservationTimestamp(timestamp);

        weatherData3 = new WeatherData();
        weatherData3.setStationName("Test Station Tartu Heavy Weather");
        weatherData3.setWmoCode("26242");
        weatherData3.setAirTemperature(-20.5);
        weatherData3.setWindSpeed(20.2);
        weatherData3.setWeatherPhenomenon("Glaze");
        weatherData3.setObservationTimestamp(timestamp);
    }

    @Test
    void saveMockWeatherData() {
        when(weatherDataRepository.save(weatherData1)).thenReturn(weatherData1);
        when(weatherDataRepository.save(weatherData2)).thenReturn(weatherData2);
        when(weatherDataRepository.save(weatherData3)).thenReturn(weatherData3);

        weatherDataService.saveWeatherData(weatherData1);
        weatherDataService.saveWeatherData(weatherData2);
        weatherDataService.saveWeatherData(weatherData3);

        verify(weatherDataRepository, times(1)).save(weatherData1);
        verify(weatherDataRepository, times(1)).save(weatherData2);
        verify(weatherDataRepository, times(1)).save(weatherData3);

        assertNotNull(weatherDataRepository.findByWmoCode("26038"));
        assertEquals("Test Station Tallinn", weatherData1.getStationName());
        assertEquals(20.5, weatherData1.getAirTemperature());
        assertEquals(5.2, weatherData1.getWindSpeed());

        assertNotNull(weatherDataRepository.findByWmoCode("41803"));
        assertEquals("Test Station Pärnu Bad Weather", weatherData2.getStationName());
        assertEquals(-10.5, weatherData2.getAirTemperature());
        assertEquals(10.2, weatherData2.getWindSpeed());
        assertEquals("Light shower", weatherData2.getWeatherPhenomenon());

        assertNotNull(weatherDataRepository.findByWmoCode("26242"));
        assertEquals("Test Station Tartu Heavy Weather", weatherData3.getStationName());
        assertEquals(-20.5, weatherData3.getAirTemperature());
        assertEquals(20.2, weatherData3.getWindSpeed());
        assertEquals("Glaze", weatherData3.getWeatherPhenomenon());
    }
    }
