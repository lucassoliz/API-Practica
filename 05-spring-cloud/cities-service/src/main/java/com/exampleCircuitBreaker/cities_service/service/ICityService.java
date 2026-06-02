package com.exampleCircuitBreaker.cities_service.service;

import com.exampleCircuitBreaker.cities_service.dto.CityDTO;

public interface ICityService {

    public CityDTO getCitiesHotels(String name, String country);

}
