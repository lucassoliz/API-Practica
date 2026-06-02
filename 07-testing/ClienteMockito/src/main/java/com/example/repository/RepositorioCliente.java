package com.example.repository;
import com.example.model.Cliente;

public interface RepositorioCliente {

    Cliente buscarPorId(long id);
    void guardar(Cliente cliente);
}