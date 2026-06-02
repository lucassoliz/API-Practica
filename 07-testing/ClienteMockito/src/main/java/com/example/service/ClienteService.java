package com.example.service;

import com.example.model.Cliente;
import com.example.repository.RepositorioCliente;

// ClienteService.java
public class ClienteService {

    private final RepositorioCliente repo;

    //inyección de dependencias
    public ClienteService(RepositorioCliente repo) {
        this.repo = repo;
    }

    public String obtenerNombreCliente(long id) {
        Cliente cli = repo.buscarPorId(id);
        return (cli != null) ? cli.getNombre() : "Desconocido";
    }

    public void registrarCliente(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        Cliente nuevo = new Cliente(nombre);
        repo.guardar(nuevo);
    }
}

