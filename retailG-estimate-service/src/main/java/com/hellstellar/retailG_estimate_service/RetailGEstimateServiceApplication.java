package com.hellstellar.retailG_estimate_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.hellstellar.retailG_estimate_service.services.ExpiredTokenService;
import com.hellstellar.retailG_estimate_service.services.ProductPriceService;



@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class RetailGEstimateServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RetailGEstimateServiceApplication.class, args);
		context.getBean(ExpiredTokenService.class).deleteExpiredToken();
		context.getBean(ProductPriceService.class).updatePrices();
	}

}
