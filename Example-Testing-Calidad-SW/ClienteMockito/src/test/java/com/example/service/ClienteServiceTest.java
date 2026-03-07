package com.example.service;

import com.example.model.Cliente;
import com.example.repository.RepositorioCliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private RepositorioCliente repo;

    @InjectMocks
    private ClienteService service;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;

    @Test
    void obtenerClienteExistente() {
        // Arrange
        when(repo.buscarPorId(1L)).thenReturn(new Cliente("Ana"));

        // Act
        String nombre = service.obtenerNombreCliente(1L);

        //Assert
        assertEquals("Ana", nombre);
    }

    @Test
    void obtenerClienteInexistente() {
        when(repo.buscarPorId(99L)).thenReturn(null);
        String nombre = service.obtenerNombreCliente(99L);
        assertEquals("Desconocido", nombre);
    }

    @Test
    void registrarClienteValido() {

        // Act
        service.registrarCliente("Juan");
        verify(repo).guardar(clienteCaptor.capture());

        //Act
        Cliente guardado = clienteCaptor.getValue();

        // Assert
        assertEquals("Juan", guardado.getNombre());
        verifyNoMoreInteractions(repo);
    }

    @Test
    void registrarClienteNombreIvalido() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> service.registrarCliente(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> service.registrarCliente("   "))
        );
    }

}
