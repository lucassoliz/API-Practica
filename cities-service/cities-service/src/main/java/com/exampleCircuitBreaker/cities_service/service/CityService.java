package com.exampleCircuitBreaker.cities_service.service;

import com.exampleCircuitBreaker.cities_service.dto.CityDTO;
import com.exampleCircuitBreaker.cities_service.model.City;
import com.exampleCircuitBreaker.cities_service.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private IHotelsAPI hotelsAPI;

    List<City> cities = new ArrayList<>();


    @Override
    @CircuitBreaker(name="hotels-service", fallbackMethod = "fellbackGetCitiesHotels")
    @Retry(name="hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {

        //buscamos ciudad original
        City city = this.findCity(name, country);

        //creamos el DTO de la ciudad + lista de hoteles
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity_id(city.getCity_id());
        cityDTO.setName(city.getName());
        cityDTO.setContinent(city.getContinent());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setState(city.getState());

        //buscamos la lista de hoteles en la API y asignamos
        cityDTO.setHotelList(hotelsAPI.getHotelsByCityId(city.getCity_id()));

        createException();

        return cityDTO;
    }

    public CityDTO fellbackGetCitiesHotels(Throwable throwable){
        return new CityDTO(99999999999L, "Fallido", "Fallido", "Fallido", "Fallido", null);
    }

    public void createException(){
        throw new RuntimeException("Fallo en la API de hoteles");
    }

    public City findCity(String name, String country){
        this.loadCities();
        for(City c:cities){
            if(c.getName().equals(name) && c.getCountry().equals(country)){
                return c;
            }
        }
        return null;
    }

    public void loadCities(){
        cities.add(new City(1L, "City 1", "Continent 1", "Country 1", "State 1"));
        cities.add(new City(2L, "City 2", "Continent 2", "Country 2", "State 2"));
        cities.add(new City(3L, "City 3", "Continent 3", "Country 3", "State 3"));
        cities.add(new City(4L, "City 4", "Continent 4", "Country 4", "State 4"));
        cities.add(new City(5L, "City 5", "Continent 5", "Country 5", "State 5"));
        cities.add(new City(6L, "City 6", "Continent 6", "Country 6", "State 6"));
        cities.add(new City(7L, "City 7", "Continent 7", "Country 7", "State 7"));
        cities.add(new City(8L, "City 8", "Continent 8)", "Country 8", "State 8"));
        cities.add(new City(9L, "City 9", "Continent 9", "Country 9", "State 9"));
        cities.add(new City(10L, "City 10", "Continent 10", "Country 10", "State 10"));

    }

}
