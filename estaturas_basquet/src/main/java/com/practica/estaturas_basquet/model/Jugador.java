
package com.practica.estaturas_basquet.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Jugador {
    
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private double peso;
    private double estatura;

    public Jugador() {
    }

    public Jugador(Long id, String dni, String nombre, String apellido, int edad, double peso, double estatura) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }
    
    
    
}
