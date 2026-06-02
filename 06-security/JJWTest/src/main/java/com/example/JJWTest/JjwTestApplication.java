package com.example.JJWTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JjwTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(JjwTestApplication.class, args);

		// construimos extra claims que se incluirán en el payload del JWT para que se puedan recuperar posteriormente
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("name", "prueba jwt");

		// construimos jwt con una fecha de emisión y una fecha de expiración (en este caso, 1 minuto después de la emisión)
		Date issuedAt = new Date(System.currentTimeMillis()); // fecha de emisión (ahora)
		Date expiration = new Date(issuedAt.getTime() + (1 * 60 * 1000)); // fecha de expiración (1 minuto después de la emisión)

		String jwt = Jwts.builder()

				// cabecera con el tipo de token (JWT) y el algoritmo de firma (HS256)
				.setHeaderParam("typ", "JWT")

				// payload con el subject (pruebajwt), la fecha de emisión, la fecha de expiración y los extra claims
				.setSubject("pruebajwt")
				.setIssuedAt(issuedAt)
				.setExpiration(expiration)
				.addClaims(extraClaims)

				// firma del JWT utilizando el algoritmo HS256 y una clave secreta generada por el metodo generateKey()
				.signWith(generateKey(), SignatureAlgorithm.HS256)

				.compact();

		// vemos nuestro jwt generado por pantalla (será una cadena de texto con tres partes separadas por puntos: la cabecera, el payload y la firma)
		System.out.println(jwt);
	}

	public static SecretKey generateKey() {
		// debe tener al menos 256 bits (32 bytes) para HS256 y ser lo suficientemente larga para evitar ataques de fuerza bruta
		String secretKey = "tengo_que_generar_una_key_super_larga_para_hs256_123456";

		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}