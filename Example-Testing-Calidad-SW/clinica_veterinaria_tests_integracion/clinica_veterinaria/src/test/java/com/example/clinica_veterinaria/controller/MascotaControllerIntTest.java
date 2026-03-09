package com.example.clinica_veterinaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.repository.IMascotaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MascotaControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IMascotaRepository mascotaRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_CREATE = "/mascotas/crear"; //SETEAMOS la url del end-point

    @Test
    void crearMascotaTest () throws Exception{

        //Arrange: construimos la entidad Mascota tal cual lo espera el controller
        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Ibra");
        nuevaMascota.setEspecie("Perro");
        nuevaMascota.setRaza("Yorkshire");
        nuevaMascota.setColor("Azul y fuego");

        //Act:
        MvcResult mvcResult = mockMvc.perform(
                        post(URL_CREATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .content(objectMapper.writeValueAsString(nuevaMascota))
                )
        //Assert (de la capa web): comprobamos estado + la respuesta en texto
                //comprobamos status code
                .andExpect(status().isOk())
                //comprobamos el tipo de dato y texto de respuesta
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("La mascota fue creada correctamente"))
                //recuperamos el resultado completo de la ejecución de la request
                .andReturn();

        //Assert (persistencia/BD)
        //buscamos en DB por campos (no tenemos id en la respuesta, sino un texto plano)
        //vamos a usar prog funcional
        Mascota enBD = mascotaRepo.findAll().stream()
                .filter(m -> "Ibra".equals(m.getNombre()))
                .filter(m -> "Perro".equals(m.getEspecie()))
                .filter(m -> "Yorkshire".equals(m.getRaza()))
                .filter(m -> "Azul y fuego".equals(m.getColor()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No se encontró la mascota persistida en la DB"));

        // Verificaciones finales
        //Asegurate que el id no sea nulo
        assertThat(enBD.getId()).isNotNull();
        //Ssegurate que el nombre sea igual a Ibra, etc
        assertThat(enBD.getNombre()).isEqualTo("Ibra");
        assertThat(enBD.getEspecie()).isEqualTo("Perro");
        assertThat(enBD.getRaza()).isEqualTo("Yorkshire");
        assertThat(enBD.getColor()).isEqualTo("Azul y fuego");
    }
}
