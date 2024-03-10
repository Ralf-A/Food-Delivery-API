package com.FujitsuFoodDeliveryAPI;

import com.FujitsuFoodDeliveryAPI.config.WeatherParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.FujitsuFoodDeliveryAPI.domain"})
@EnableJpaRepositories(basePackages = {"com.FujitsuFoodDeliveryAPI.repository"})public class FoodDeliveryApiApplication {

	public static void main(String[] args) {
		WeatherParser weatherParser;
		SpringApplication.run(FoodDeliveryApiApplication.class, args);
	}
}
