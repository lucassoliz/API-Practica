
package com.example.springSecurityBasic.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // AQUI está el cambio: Se abre un paréntesis y se usa "auth ->"
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/holanoseg").permitAll()
                        .anyRequest().authenticated()
                )
                // Lo mismo para el login, se usa una lambda
                .formLogin(form -> form.permitAll())
                .build();
    }
}
