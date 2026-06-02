package com.ejemplo.jpaDemo.Service;

import com.ejemplo.jpaDemo.Model.Persona;
import java.util.List;

public interface IPersonaService {
    //metodos abstractos por ser interface

    //método para traer todas las personas
    public List<Persona> getPersonas();

    //alta
    public void savePersona(Persona perso);

    //baja
    public void deletePersona(Long id);

    //lectura de un solo objeto
    public Persona findPersona(Long id);

    //edición/modificación
    public void editPersona(Long idOriginal, Long idNueva, String nuevoNombre, String nuevoApellido, int nuevaEdad);
}
