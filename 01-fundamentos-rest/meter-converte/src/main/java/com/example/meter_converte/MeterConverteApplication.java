package com.example.meter_converte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MeterConverteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeterConverteApplication.class, args);
	}

}
