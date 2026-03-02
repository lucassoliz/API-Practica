package com.example.springSecurityBasic.security.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springSecurityBasic.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

//mediante el extends establecemos que es un filtro que se tiene que ejecutar siempre que se haga
// una petición, y el metodo doFilterInternal es el que se va a ejecutar cada vez que se haga
// una petición, y ahí es donde vamos a validar el token
public class JwtTokenValidator extends OncePerRequestFilter {

//inyectamos el JwtUtils para poder usar el metodo de validacin del token, y también para extraer
// los datos del token, como el usernme y los permisos
    private JwtUtils jwtUtils;
//el constructor es necesario para inyectar el JwtUtils, porque no podemos usar la anotación de
// @Autowired en un filtro, ya que no es un componente de Spring, sino que es un filtro que se ejecuta
// antes de que se cree el contexto de Spring, por lo que no se pueden inyectar dependencias en él, por eso
// necesitamos un constructor para inyectar el JwtUtils
    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    //importante: el nonnull debe ser de sringframework, no lombok porque es el que se usa en el proyecto, y el de lombok es para anotaciones de validación
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null) {
            //en el encabezado antes del token viene la palabra bearer (esquema de autenticación)
            //por lo que debemos sacarlo para quedarnos solo con el token, que es lo que necesitamos validar
            jwtToken = jwtToken.substring(7); //son 7 letras + 1 espacio
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            //si el token es válido, le concedemos el acceso al usuario, y para eso necesitamos obtener el username y
            // los permisos del token, que es lo que vamos a setear en el context holder
            String username = jwtUtils.extractUsername(decodedJWT);
            //me devuelve claim, necesito pasarlo a String para poder usarlo, y el claim que quiero es el de
            // authorities, que es el que definimos en el metodo de creación del token
            String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            //Si todoo esta ok, hay que setearlo en el Context Holder
            //Para eso tengo que convertirlos a GrantedAuthority para que Spring Security los pueda entender, y para eso uso el
            //metodo de AuthorityUtils, que me convierte una cadena de texto con los permisos separados por coma en
            // una colección de objetos de tipo GrantedAuthority
            Collection<GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            //Si se valida el token, le damos acceso al usuario en el context holder y le asignamos los
            // permisos que tiene, para que pueda acceder a los recursos protegidos
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authoritiesList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }

        // si no viene token, va al siguiente filtro
        //si no viene el token, esto arroja error
        filterChain.doFilter(request,response);
    }
}
