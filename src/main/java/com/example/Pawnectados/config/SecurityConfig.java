package com.example.Pawnectados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Necesario para login con fetch/AJAX

                .authorizeHttpRequests(auth -> auth

                        // ============================
                        //    RUTAS PÚBLICAS
                        // ============================
                        .requestMatchers(
                                "/", "/Home", "/home",
                                "/login", "/api/login", "/api/registro",

                                // Recuperación de contraseña
                                "/principal/recuperar/**",
                                "/restablecer/**",

                                // Archivos estáticos
                                "/CSS/**", "/JS/**", "/IMG/**"
                        ).permitAll()

                        // ============================
                        //       RUTAS POR ROL
                        // ============================
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/fundacion/**").hasAuthority("ROLE_FUNDACION")
                        .requestMatchers("/veterinaria/**").hasAuthority("ROLE_VETERINARIA")
                        .requestMatchers("/usuarios/**")
                        .hasAnyAuthority("ROLE_USER", "ROLE_PERSONA_JURIDICA")

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )

                // ============================
                //    LOGIN PERSONALIZADO
                //    (Desactivamos formLogin porque usas fetch)
                // ============================
                .formLogin(form -> form.disable());


        return http.build();
    }
}
