package com.metodoDTO.controller;

import com.metodoDTO.practica.Inquilino;
import com.metodoDTO.practica.Propiedad;
import com.metodoDTO.practica.PropiedadDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class aplicacionController {

    @GetMapping("/propiedad/{id}")
    @ResponseBody
    public PropiedadDTO devolverPropiedad(@PathVariable Long id) {
        //que a traves de la id buscamos la propiedad
        //traemos al inquilino asociado a esa propiedad

        Propiedad prop = new Propiedad(1212L, "Casa", "308 Sarmiento", 200.0, 4000.0);
        Inquilino inqui = new Inquilino(1L, "12345", "walter","white", "Profe de quimica");
        
        PropiedadDTO propiDTO = new PropiedadDTO();
        
        //asignamos los datos que necesitamos de propiedad
        propiDTO.setApellido(inqui.getApellido());
        propiDTO.setId_propiedad(prop.getId_propiedad());
        propiDTO.setDireccion(prop.getDireccion());
        propiDTO.setValor_alquiler(prop.getValor_alquiler());
        //y aca de inquilino
        propiDTO.setNombre(inqui.getNombre());
        propiDTO.setApellido(inqui.getApellido());
        
        return propiDTO;

    }

}
