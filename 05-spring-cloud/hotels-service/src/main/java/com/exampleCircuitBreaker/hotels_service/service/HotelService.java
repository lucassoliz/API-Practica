package com.exampleCircuitBreaker.hotels_service.service;

import com.exampleCircuitBreaker.hotels_service.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    List<Hotel> hotelsList = new ArrayList<>();

    @Override
    public List<Hotel> getHotelsByCityId(Long city_Id) {

        //carga la BD "lógica"
        this.loadHotels();

        //busca si coincide la id
        List<Hotel> hotelCityList = new ArrayList<>();
        for(Hotel h:hotelsList){
            if(h.getCity_Id()==(city_Id)){
                hotelCityList.add(h);
            }
        }

        //devuelve la lista
        return hotelCityList;


    }

    public void loadHotels(){
        hotelsList.add(new Hotel(1L, "Hotel 1",5, 1L));
        hotelsList.add(new Hotel(2L, "Hotel 2", 6,1L));
        hotelsList.add(new Hotel(3L, "Hotel 3",3, 2L));
        hotelsList.add(new Hotel(4L, "Hotel 4", 1, 2L));
        hotelsList.add(new Hotel(5L, "Hotel 5", 1, 2L));
        hotelsList.add(new Hotel(6L, "Hotel 6", 2, 2L));
        hotelsList.add(new Hotel(7L, "Hotel 7", 5, 2L));
        hotelsList.add(new Hotel(8L, "Hotel 8", 1, 2L));
        hotelsList.add(new Hotel(9L, "Hotel 9", 2, 2L));
    }


}
