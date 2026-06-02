
package com.consultora.paciente.repository;

import com.consultora.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long>{
    //estamos usando un metodo predeterminado de JPA
    //Pero no contempla el de DNI, por lo que lo tenemos que crear nosotros:
    
    @Query("SELECT pac FROM Paciente pac WHERE pac.dni = :dni")
    Paciente findByDni(String dni);
}
