package com.example.springSecurityBasic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
//VALUE NO DEBE SER EL DE LOMBOK, SINO EL DE SPRING, PORQUE ES EL QUE SE INYECTA EN EL PROYECTO!!!
        //Con estas configuraciones aseguramos la autenticidad del token a crear
        @Value("${security.jwt.private.key}")
        private String privateKey;

        @Value("${security.jwt.user.generator}")
        private String userGenerator;

    //Para encriptar, vamos a necesitar esta clave secreta y este algoritmo de encriptación, que
    // lo vamos a usar para generar el token
    public String createToken (Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey); //algoritmo de encriptación, con la clave secreta que definimos en el application.properties

        //esto está dentro del security context holder
        String username = authentication.getPrincipal().toString();

        //también obtenemos los permisos/autorizaciones
        //la idea es traer los permisos separados por coma y guardarlos en una variable, para luego incluirlos en el token
        String authorities = authentication.getAuthorities()
                .stream()//hacemos un stream de las autoridades, que es una colección de objetos de tipo GrantedAuthority
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); //con esto obtenemos una cadena de texto con los permisos separados por coma, que es lo que queremos incluir en el token

        //a partir de esto generamos el token con la librería de JWT, con los datos que queremos incluir en el
        // token, y con la firma que es el algoritmo de encriptación
        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator) //acá va el usuario que genera el token
                .withSubject(username) // a quien se le genera el token
                .withClaim("authorities", authorities) //claims son los datos contraidos en el JWT
                .withIssuedAt(new Date()) //fecha de generación del token
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) //fecha de expiración, tiempo en milisegundos
                .withJWTId(UUID.randomUUID().toString()) //id al token - que genere una random
                .withNotBefore(new Date(System.currentTimeMillis())) //desde cuando es válido (desde ahora en este caso)
                .sign(algorithm); //nuestra firma es la que creamos con la clave secreta

        return jwtToken;
    }

}
