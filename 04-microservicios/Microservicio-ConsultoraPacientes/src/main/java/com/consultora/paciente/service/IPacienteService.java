package com.consultora.paciente.service;

import com.consultora.paciente.model.Paciente;
import java.util.List;

public interface IPacienteService {

    public List<Paciente> getPaciente();

    public void savePaciente(Paciente pac);

    public void deletePaciente(Long id);

    public Paciente findPaciente(Long id);

    public void editPaciente(Long id, Paciente pac);

    public Paciente findPacienteDni(String dni);
}
