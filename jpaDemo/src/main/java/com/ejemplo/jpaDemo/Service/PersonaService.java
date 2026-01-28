
package com.ejemplo.jpaDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.jpaDemo.Repository.IPersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private IPersonaRepository persoReposs;
}
