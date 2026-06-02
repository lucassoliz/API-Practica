package com.codigoprobando.odontologo.controller;

import com.todocodeacademy.odontologo.Paciente;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OdontologoController {
    
    @GetMapping ("/pacientes")
    public List<Paciente> traerPacientes () {
        //Simulamos lista lógica de pacientes como si fuera una BD
        List<Paciente> listaPacientes = new ArrayList<Paciente>();
        
        listaPacientes.add(new Paciente(1L, "52333654", "Yasmín", "Romero", LocalDate.of(2016,8,18)));
        listaPacientes.add(new Paciente(2L, "13558965", "Sara", "Barrios",LocalDate.of(1960,1,20)));
        listaPacientes.add(new Paciente(3L, "49663221", "Benjamín", "Goichuk", LocalDate.of(2008,11,25)));
        listaPacientes.add(new Paciente(4L, "50111889", "Gladys", "Andreiszyn", LocalDate.of(2015,9,27)));
        listaPacientes.add(new Paciente(5L, "12000320", "Eduardo", "Malim", LocalDate.of(1958,5,13)));
        
        //devuelvo toda la lista de pacientes
        return listaPacientes;
    }
    
     @GetMapping ("/pacientes/menores")
     @ResponseBody
     public List<Paciente> traerMenores() {
         List<Paciente> listaPacientes = new ArrayList<Paciente>();
         listaPacientes.add(new Paciente(1L, "52333654", "Yasmín", "Romero", LocalDate.of(2016,8,18)));
        listaPacientes.add(new Paciente(2L, "13558965", "Sara", "Barrios",LocalDate.of(1960,1,20)));
        listaPacientes.add(new Paciente(3L, "49663221", "Benjamín", "Goichuk", LocalDate.of(2008,11,25)));
        listaPacientes.add(new Paciente(4L, "50111889", "Gladys", "Andreiszyn", LocalDate.of(2015,9,27)));
        listaPacientes.add(new Paciente(5L, "12000320", "Eduardo", "Malim", LocalDate.of(1958,5,13)));
         
         //creo lista para guardar a los menores
         List<Paciente> listaMenores = new ArrayList<Paciente>();
         
         for (Paciente pac : listaPacientes) {
            
             //fecha de hoy
            LocalDate hoy = LocalDate.now();
            //calculo período entre fecha de nacimiento del paciente y fecha de hoy 
            Period cant_anios = Period.between(pac.getFecha_nacimiento(), hoy);
             
            //si es menor de 18 lo agrego a lista menores
            if (cant_anios.getYears()<18) {
                System.out.println("cant anios " + cant_anios.getYears());
                listaMenores.add(pac);
            }
         
         }
         return listaMenores;
     }
    
}
