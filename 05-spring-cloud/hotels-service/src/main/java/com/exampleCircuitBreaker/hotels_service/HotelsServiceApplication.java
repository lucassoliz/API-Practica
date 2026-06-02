package com.exampleCircuitBreaker.hotels_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class HotelsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsServiceApplication.class, args);
	}

}
