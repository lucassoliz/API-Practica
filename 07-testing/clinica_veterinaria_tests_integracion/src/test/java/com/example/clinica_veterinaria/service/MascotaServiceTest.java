package com.example.clinica_veterinaria.service;

import com.example.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.repository.IMascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MascotaServiceTest {

    @Mock
    private IMascotaRepository repoMasco;
    @InjectMocks
    private MascotaService service;

    @Test
    void saveMascotaTest() {

        //creamos objeto mascota
        Mascota mascota = new Mascota(null, "Ibra", "Perro", "Yorkshire", "Azul y fuego");

        //llamamos al guardado
        service.saveMascota(mascota);

        //verificamos que se haya guardado
        verify(repoMasco).save(mascota);
        //verificamos que no hayan más interacciones
        verifyNoMoreInteractions(repoMasco);
    }

    @Test
    void getMascotasTest() {

        // Arrange
        //Creamos una lista de mascotas ficticia para usar para comprobar
        Mascota masco1 = new Mascota(1L, "Ibra", "Perro", "Yorkshire", "Azul y fuego");
        Mascota masco2 = new Mascota(2L, "Gaia", "Perro", "Ovejero Alemán", "Negro y fuego");
        when(repoMasco.findAll()).thenReturn(List.of(masco1, masco2));

        // Act (llamada ficticia al service)
        List<Mascota> listaMascotas = service.getMascotas();

        // Asserts (JUnit)
        assertNotNull(listaMascotas);
        assertEquals(2, listaMascotas.size());
        assertEquals(1L, listaMascotas.get(0).getId());
        assertEquals("Ibra", listaMascotas.get(0).getNombre());
        assertEquals(2L, listaMascotas.get(1).getId());
        assertEquals("Gaia", listaMascotas.get(1).getNombre());
        verify(repoMasco).findAll();
        verifyNoMoreInteractions(repoMasco);

    }

}
