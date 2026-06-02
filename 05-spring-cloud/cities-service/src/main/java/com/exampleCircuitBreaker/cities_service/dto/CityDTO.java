package com.exampleCircuitBreaker.cities_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long city_id;
    private String name;
    private String continent;
    private String country;
    private String state;
    private List<HotelDTO> hotelList;

}
