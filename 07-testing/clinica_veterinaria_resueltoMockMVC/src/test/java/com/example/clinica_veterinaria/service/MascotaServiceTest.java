package com.example.clinica_veterinaria.service;

import com.example.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.repository.IMascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

}
