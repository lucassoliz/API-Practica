package com.codigoprobando.odontologo;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paciente {
    
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;

    public Paciente() {
    }

    public Paciente(Long id, String dni, String nombre, String apellido, LocalDate fecha_nacimiento) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
    }

 
    
    
    
    
}
