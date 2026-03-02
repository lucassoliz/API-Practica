package com.example.springSecurityBasic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
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
//===========================================================================
//metodo para decodificar y validar el token, que va a ser el que se use en el filtro de seguridad
// para validar el token que viene en la petición
public DecodedJWT validateToken(String token) {

    try {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey); //algoritmo + clave privada para validar el token, que es el mismo que se usó para generarlo
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(this.userGenerator)
                .build(); //usa patrón builder para crear el verificador, con el algoritmo y el issuer que definimos, que es el mismo que se usó para generar el token

        //si esta toodo ok, no genera excepción y hace el return del token decodificado, que es un objeto de
        // tipo DecodedJWT, que tiene toda la información del token, como el subject, los claims, etc
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }
    catch (JWTVerificationException exception) {
        throw new JWTVerificationException("Invalid token. Not authorized");
    }
}
//===========================================================================
    //devuelvo el subject del token, que es el username, a partir del token decodificado
public String extractUsername (DecodedJWT decodedJWT) {
    //el subject es el usuario según establecimos al crear el token
    return decodedJWT.getSubject().toString();
}

//===========================================================================
//devuelvo un claim en particular a partir del token decodificado, por ejemplo el claim de authorities, que es el que contiene los permisos del usuario
public Claim getSpecificClaim (DecodedJWT decodedJWT, String claimName) {
    return decodedJWT.getClaim(claimName);
}

    //devuelvo todos los claims del token decodificado, que es un mapa de String (nombre del claim) y Claim (valor del claim), para poder acceder a todos los datos que contiene el token
    public Map<String, Claim> returnAllClaims (DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }





}
