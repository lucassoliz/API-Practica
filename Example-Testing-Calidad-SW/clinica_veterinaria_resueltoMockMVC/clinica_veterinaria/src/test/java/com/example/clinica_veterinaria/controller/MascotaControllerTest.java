package com.example.clinica_veterinaria.controller;

import com.example.clinica_veterinaria.dto.MascoDuenioDTO;
import com.example.clinica_veterinaria.service.IMascotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(MascotaController.class)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMascotaService mascoServ;

    @Test
    void traerMascoDueniosTest() throws Exception {
        MascoDuenioDTO dto = new MascoDuenioDTO("Ibra", "Perro", "Yorkshire", "Luisina", "TodoCode");
        //Esto es stubbing con Mockito: estás diciendo “cuando se llame a este método del doble de prueba, devolvé esto”.
        when(mascoServ.getMascoDuenios()).thenReturn(List.of(dto));

        mvc.perform(get("/mascotas/traer-duenios").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre_mascota").value("Ibra"))
                .andExpect(jsonPath("$[0].nombre_duenio").value("Luisina"))
                .andExpect(jsonPath("$[0].apellido_duenio").value("TodoCode"));
    }

}
