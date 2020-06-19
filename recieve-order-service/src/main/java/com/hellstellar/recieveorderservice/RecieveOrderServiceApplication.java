package com.hellstellar.recieveorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.hellstellar.recieveorderservice.service.ExpiredTokenService;

@SpringBootApplication
@EnableEurekaClient
public class RecieveOrderServiceApplication {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RecieveOrderServiceApplication.class, args);
		context.getBean(ExpiredTokenService.class).deleteExpiredToken();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
