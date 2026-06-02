
package com.consultora.turnos.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {
    private LocalDate fecha;
    private String tratamiento;
    private String dniPaciente;
    
    
}
