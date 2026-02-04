
package com.consultora.turnos.controller;

import com.consultora.turnos.model.Turno;
import com.consultora.turnos.service.ITurnoService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class Controller {
    
    @Autowired
    private ITurnoService turnoServ;
    
    //1 - crear un nuevo turno
    @PostMapping("/crear")
    public String crearTurno (@RequestBody LocalDate fecha,
                              @RequestBody String tratamiento,
                              @RequestBody String dniPaciente){
        turnoServ.saveTurno(fecha, tratamiento, dniPaciente);
        
        return "Turno creado correctamente";
    }
    
    // 2 - obetener todos los turnos
    @GetMapping("/traer")
    public List<Turno> traerTurnos(){
        return turnoServ.getTurnos();
    }
    
    // 3 - Eliminar un turno
    @DeleteMapping("/borrar/{id}")
    public String deleteTurno(@PathVariable Long id){
        turnoServ.deleteTurno(id);
        return "El turno fue eliminado correctamente";
        
    }
    
    // 4 - Editar turno
    @PutMapping("/editar/{id_original}")
    public Turno editTurno (@PathVariable Long id_original,
                            @RequestBody Turno turnoEditar){
        turnoServ.editTurno(id_original, turnoEditar);
        Turno turnoEditado = turnoServ.findTurno(id_original);
        
        return turnoEditado;
    }
    
    //5 - obtener un turno en particular
    @GetMapping("/traer/{id}")
    public Turno traerTurno(@PathVariable Long id){
        return turnoServ.findTurno(id);
    }
    
}
