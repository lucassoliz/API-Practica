package com.example.springSecurityBasic.dto;


import jakarta.validation.constraints.NotBlank;
//Cuando una clase se declara como un registro, el compilador de Java genera automáticamente
public record AuthLoginRequestDTO (@NotBank String username, @NotBlank String password) {
}
