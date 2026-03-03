package com.example.springSecurityBasic.controller;

import com.example.springSecurityBasic.service.UserDetailsServiceImp;

import com.example.springSecurityBasic.dto.AuthLoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
// Este controlador se encarga de manejar las peticiones de autenticación, en este caso, solo vamos a tener una petición de login, pero podríamos agregar otras como register, refresh token, etc.
        @Autowired
        private UserDetailsServiceImp userDetailsService;

        //Todas estas requests y responses vamos a tratarlas como dto
        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
            return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
        }

    }



