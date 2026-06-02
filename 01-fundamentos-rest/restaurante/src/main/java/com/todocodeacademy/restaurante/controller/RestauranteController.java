package com.todocodeacademy.restaurante.controller;

import com.todocodeacademy.restaurante.Plato;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestauranteController {
    
    @GetMapping ("/platos/{num_plato}")
    @ResponseBody
    public Plato traerPlatos (@PathVariable int num_plato) {
        List<Plato> listaPlatos = new ArrayList<Plato>();
        //simulamos la bd lógica agregando elementos a la lista
        listaPlatos.add(new Plato (1, "Milanesas con Puré",500.00,"Milanesa simple, sin napolitana con puré de papas"));
        listaPlatos.add(new Plato (2, "Ravioles con Salsa Bolognesa",850.00,"Ravioles de verdura o ricota con salsa bolognesa y queso rallado"));
        listaPlatos.add(new Plato (3, "Lomo a la mostaza con papas fritas",1200.00,"Bides de lomo con salsa de mostaza y papas fritas"));
        listaPlatos.add(new Plato (4, "Ensalada mixta",350.00,"Ensalada de lechuga, tomate y cebolla"));
        listaPlatos.add(new Plato (5, "Milanesas con arroz primavera",500.00,"Milanesa simple, sin napolitana con arroz y verduras"));

             //buscamos en la lista de platos a ver si hay uno con esa id
            for (Plato plat : listaPlatos) { 
                if (plat.getNum() == num_plato) {
                    //Si encontró devuelve el plato completo
                    return plat; 
                }    
            }
           
        //si no encontró nada, devuelve null
        return null;
    }
    
    
    
}
