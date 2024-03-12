package com.FujitsuFoodDeliveryAPI.Food.Delivery.API;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URLEncoder;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculateDeliveryFeeAPITests {
    @Test
    public void testCalculateDeliveryFeeTallinn() {
        // Calculate delivery fee for Tallinn and Car on default settings
        String city = "Tallinn";
        String vehicle = "car";
        double expectedFee = 4.0;
        double actualFee = given()
                .queryParam("city", city)
                .queryParam("vehicle", vehicle)
                .when()
                .get("http://localhost:8080/calculateDeliveryFee")
                .then()
                .statusCode(200)
                .extract()
                .as(Double.class);
        assertEquals(expectedFee, actualFee);
    }
    @Test
    public void testCalculateDeliveryFeeTallinnCityError() {
        // Test that error 400 and No current weather data found for the specified city is returned when city is wrong
        String city = "aaafasfsaa";
        String vehicle = "car";
        String expectedError = "{\"error\":\"No current weather data found for the specified city.\"}";
        String error = given()
                .queryParam("city", city)
                .queryParam("vehicle", vehicle)
                .when()
                .get("http://localhost:8080/calculateDeliveryFee")
                .then()
                .statusCode(404)
                .extract()
                .body()
                .asString();

        assertEquals(expectedError, error);
    }

    @Test
    public void testCalculateDeliveryFeeTallinnVehicleError() {
        // Test that error 400 and No current weather data found for the specified city is returned when vehicle type is wrong
        String city = "Tallinn";
        String vehicle = "adsdfafsafsf";
        String expectedError = "{\"error\":\"Invalid vehicle type\"}";
        String error = given()
                .queryParam("city", city)
                .queryParam("vehicle", vehicle)
                .when()
                .get("http://localhost:8080/calculateDeliveryFee")
                .then()
                .statusCode(400)
                .extract()
                .body()
                .asString();

        assertEquals(expectedError, error);
    }

    @Test
    public void testCalculateDeliveryFeeTallinnInvalidParameterNames() {
        // Test that error 400 and No current weather data found for the specified city is returned when parameter is missing or wrong type
        String city = "Tallinn";
        String vehicle = "car";
        String expectedError = "{\"error\":\"The parameter 'vehicle' is required.\"}";
        String error = given()
                .queryParam("city", city)
                .queryParam("aaa", vehicle)
                .when()
                .get("http://localhost:8080/calculateDeliveryFee")
                .then()
                .statusCode(400)
                .extract()
                .body()
                .asString();

        assertEquals(expectedError, error);
    }

    @Test
    public void testCalculateDeliveryFeeNoParameterNames() {
        // Test that error 400 and No current weather data found for the specified city is returned when parameter is missing or wrong type
        String expectedError = "{\"error\":\"The parameter 'city' is required.\"}";
        String error = given()
                .when()
                .get("http://localhost:8080/calculateDeliveryFee")
                .then()
                .statusCode(400)
                .extract()
                .body()
                .asString();

        assertEquals(expectedError, error);
    }
}
