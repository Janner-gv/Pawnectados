package com.example.Pawnectados.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("/admin/Dashboard");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FUNDACION"))) {
            response.sendRedirect("/fundacion/fundaciones");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VETERINARIA"))) {
            response.sendRedirect("/veterinaria/veterinarias");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            response.sendRedirect("/usuarios/usuarios");
        } else {
            response.sendRedirect("/home"); // fallback público
        }
    }
}
