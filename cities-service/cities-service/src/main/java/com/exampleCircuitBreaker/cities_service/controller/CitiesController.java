package com.exampleCircuitBreaker.cities_service.controller;

import com.exampleCircuitBreaker.cities_service.dto.CityDTO;
import com.exampleCircuitBreaker.cities_service.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/hotels")
    public CityDTO getCityAndHotels(@RequestParam String name,@RequestParam String country) {
        return cityService.getCitiesHotels(name, country);
    }
}
