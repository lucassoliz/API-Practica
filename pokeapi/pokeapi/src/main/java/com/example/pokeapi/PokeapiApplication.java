package com.example.pokeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeapiApplication.class, args);
	}

}
