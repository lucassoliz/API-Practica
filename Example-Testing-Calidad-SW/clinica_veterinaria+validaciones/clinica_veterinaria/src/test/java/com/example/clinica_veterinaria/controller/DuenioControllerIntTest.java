package com.example.clinica_veterinaria.controller;

import com.example.clinica_veterinaria.model.Duenio;
import com.example.clinica_veterinaria.repository.IDuenioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DuenioControllerIntTest {

    //url que estamos usando para el end-point
    private static final String URL_LIST = "/duenio/traer";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDuenioRepository duenioRepo;

    @Test
    void traerDueniosTest() throws Exception {

        // Arrange: persistimos un dueño de prueba
        Duenio duenio = new Duenio();
        duenio.setDni("12345678");
        duenio.setNombre("Luisina");
        duenio.setApellido("TodoCode");
        duenio.setCelular("54879654123");
        duenioRepo.save(duenio);

        // Act + Assert (capa web): pedimos el dueño por ID
        // y validamos JSON de respuesta
        mockMvc.perform(get(URL_LIST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//status code 200
                //que el tipo de respuesta sea JSON
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //ocupamos jsonPath para evaluar cada parte del json esperado y recibido
                /*
                la expresión [*] en JSONPath significa “todos los elementos de la lista”.
                Lo usamos cuando no queremos depender del orden, sino verificar que el valor esté en alguno de los elementos de la respuesta.
                * */
                .andExpect(jsonPath("$[*].dni", hasItem("12345678")))
                .andExpect(jsonPath("$[*].nombre", hasItem("Luisina")))
                .andExpect(jsonPath("$[*].apellido", hasItem("TodoCode")))
                .andExpect(jsonPath("$[*].celular", hasItem("54879654123")));
    }


}
