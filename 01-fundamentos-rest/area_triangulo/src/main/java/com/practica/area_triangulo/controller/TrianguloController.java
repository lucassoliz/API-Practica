
package com.practica.area_triangulo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrianguloController {
    
    
    @GetMapping ("/area_triangulo/{base}/{altura}")
    public String calcularAreaTriangulo (@PathVariable double base,
                                         @PathVariable double altura)
    {
        double area=0.0;
        area = (base * altura)/2;
        
        return "El área del triángulo en base a la base y altura proporcionados es: " + area;
    }
    
}
