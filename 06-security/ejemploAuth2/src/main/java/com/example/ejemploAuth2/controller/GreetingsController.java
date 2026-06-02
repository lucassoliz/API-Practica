package com.example.ejemploAuth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/sayhi")
    @PreAuthorize("permitAll()")
    public String sayHi() {
        return "Hi! Esto es un Endpoint no securizado";
    }

        @GetMapping("/sayhisec")
        @PreAuthorize("isAuthenticated()")
    public String sayHiSec() {
        return "Hi! Esto es un Endpoint securizado";
    }

}
