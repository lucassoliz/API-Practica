package com.example.clinica_veterinaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id; // es generado por JPA: no es necesario validar acá

    @NotBlank (message = "El nombre es obligatorio")
    @Size(max = 60, message = "El nombre no debe superar los 60 caracteres")
    @Pattern(
            regexp = "^[\\p{L} .'-]+$", //acá usamos expresiones regulares para indicar los símbolos que se permiten para el nombre
            message = "El nombre solo puede contener letras, espacios, punto, apóstrofe y guión"
    )
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Size(max = 40, message = "La especie no debe superar 40 caracteres")
    private String especie;

    @Size(max = 60, message = "La raza no debe superar 60 caracteres")
    @Pattern(
            regexp = "^[\\p{L} .'-]*$",
            message = "La raza solo puede contener letras, espacios, punto, apóstrofe y guion"
    )
    private String raza;

    @Size(max = 40, message = "El color no debe superar 40 caracteres")
    @Pattern(
            regexp = "^[\\p{L} ]*$",
            message = "El color solo puede contener letras y espacios"
    )
    private String color;
    
    @OneToOne
    @JoinColumn (name="id_duenio", referencedColumnName = "id_duenio")
    @Valid //Con este @Valid tenemos que validar que el dueño que llegue y se asigne, tenga formato correcto
    private Duenio duenio; //EL formato lo tendríamos que especificar en la clase Duenio

    public Mascota() {
    }

    public Mascota(Long id, String nombre, String especie, String raza, String color) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
    }
    
    

}
