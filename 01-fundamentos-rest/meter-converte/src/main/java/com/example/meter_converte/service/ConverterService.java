
package com.example.meter_converte.service;

import org.springframework.stereotype.Service;

@Service
public class ConverterService implements IConverterService{

    @Override
    public Double convertCentimeters(Double meters) {
        return meters*100;
    }
    
}
