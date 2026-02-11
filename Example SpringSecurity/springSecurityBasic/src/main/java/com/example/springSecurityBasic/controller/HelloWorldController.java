package com.example.springSecurityBasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/holaseg")
    public String secHelloWorld(){
        return "Hola Mundo con seguridad";
    }

    @GetMapping("/holanoseg")
    public String noSecHelloWorld() {
        return "Hola Mundo sin seguridad";
    }

}
