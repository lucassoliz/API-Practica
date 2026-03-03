package com.example.springSecurityBasic.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*Cuando una clase se declara como un registro, el compilador de Java genera automáticamente
ciertos métodos como el constructor, los métodos equals(), hashCode() y toString(),
basados en los componentes de datos declarados en la clase.*/
@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO (String username, String message, String jwt, boolean status) {

}
