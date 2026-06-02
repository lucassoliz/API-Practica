package com.example.springSecurityBasic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()") // Esto deniega el acceso a todos los métodos de este controlador
public class HelloWorldController {

    @GetMapping("/holaseg")
    @PreAuthorize("hasAuthority('READ')") // Esto permite el acceso solo a usuarios con el rol "READ"
    public String secHelloWorld(){
        return "Hola Mundo con seguridad";
    }

    @GetMapping("/holanoseg")
    @PreAuthorize("permitAll()") // Esto permite el acceso a todos los usuarios, incluso sin autenticación
    public String noSecHelloWorld() {
        return "Hola Mundo sin seguridad";
    }

}
