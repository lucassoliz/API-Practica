package com.example.clinica_veterinaria.repository;

import com.example.clinica_veterinaria.model.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDuenioRepository extends JpaRepository <Duenio, Long>{
    
}
