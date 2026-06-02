
package com.ejemplo.jpaDemo.Controller;

import com.ejemplo.jpaDemo.Model.Persona;
import com.ejemplo.jpaDemo.Service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    
    @Autowired
    private IPersonaService persoServ;
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersonas(){
        return persoServ.getPersonas();
    }
    
    @PostMapping("/personas/crear")
    public String savePersonas(@RequestBody Persona perso){
        
        persoServ.savePersona(perso);
        
        return "La persona fue creada correctamente";
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        persoServ.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PutMapping("/personas/editar/{id_original")
    public Persona editPersona(@PathVariable Long id_original ,
            @RequestParam(required=false, name = "id") Long nuevaId, //normalmente no es común que se modifique la id
            @RequestParam(required=false, name = "nombre") String nuevoNombre,
            @RequestParam(required=false, name = "apellido") String nuevoApellido,
            @RequestParam(required=false, name = "edad") int nuevaEdad){
        
        persoServ.editPersona(id_original, nuevaId, nuevoNombre, nuevoApellido, nuevaEdad);
        
        Persona perso = persoServ.findPersona(nuevaId);
        
        return perso;
    }
    
}
