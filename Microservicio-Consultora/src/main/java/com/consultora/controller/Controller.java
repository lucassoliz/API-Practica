
package com.consultora.controller;

import com.consultora.model.Paciente;
import com.consultora.service.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    
    @Autowired
    private IPacienteService pacienteServ;
    
    //crear un nuevo paciente
    @PostMapping("/pacientes/crear")
    public String crearPaciente(@RequestBody Paciente pac){
        pacienteServ.savePaciente(pac);
        return "Paciente creado correctamente";
    }
    
    //Obtener todos los pacientes
    @GetMapping("/pacientes/traer")
    public List<Paciente> traerPacientes(){
        return pacienteServ.getPaciente();
    }
    
    //eliminar un paciente
    @DeleteMapping("/pacientes/borrar/{id}")
    public String deletePaciente(@PathVariable Long id){
        pacienteServ.deletePaciente(id);
        
        return "El paciente fue eliminado correctamente";
        
    }
    
    //Editar paciente
    @PutMapping("/pacientes/editar/{id_original}")
    public Paciente editPaciente(@PathVariable Long id_original, 
                                 @RequestBody Paciente pacienteEditar){
        pacienteServ.editPaciente(id_original, pacienteEditar);
        Paciente pacienteEditado = pacienteServ.findPaciente(id_original);
        
        return pacienteEditado;
    }
    
    
}
