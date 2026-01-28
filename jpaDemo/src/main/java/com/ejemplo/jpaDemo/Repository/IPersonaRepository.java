
package com.ejemplo.jpaDemo.Repository;

import com.ejemplo.jpaDemo.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //mapeamos como repositorio
//la interface extiende de JpaRepository (que maneja repositorios JPA)
//en los parámetros <> deben ir <clase a persistir, tipo de dato de su id>
public interface IPersonaRepository extends JpaRepository<Persona,Long>{
    
}
