package com.example.clinica_veterinaria.controller;

import com.example.clinica_veterinaria.dto.MascoDuenioDTO;
import com.example.clinica_veterinaria.model.Duenio;
import com.example.clinica_veterinaria.model.Mascota;
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

    @Test
    void traerCanichesTest() throws Exception {

        //Arrange (creamos dos mascotas y dos dueños, para probar que solo traiga al caniche).
        Duenio duenio = new Duenio(10L, "123", "Lucy", "Goldak", "910111");
        Mascota mascota = new Mascota(1L, "Avril", "Perro", "Caniche", "Blanco");
        mascota.setDuenio(duenio);

        Duenio duenio2 = new Duenio(11L, "456", "Luisina", "TodoCode", "45678");
        Mascota mascota2 = new Mascota(2L, "Gaia", "Perro", "Ovejero Alemán", "Negro y fuego");
        mascota2.setDuenio(duenio2);

        //preparamos todo para el test
        when(mascoServ.getCaniches()).thenReturn(List.of(mascota,mascota2));

        //ejecutamos
        mvc.perform(get("/mascotas/traer-caniches").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Avril"))
                .andExpect(jsonPath("$[0].raza").value("Caniche"));

    }
}
