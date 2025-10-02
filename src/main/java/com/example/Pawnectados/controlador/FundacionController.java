package com.example.Pawnectados.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fundacion")
public class FundacionController {

    private boolean esFundacion(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_FUNDACION"));
    }

    @GetMapping("/fundaciones")
    public String fundaciones(Authentication auth) {
        return esFundacion(auth) ? "fundacion/fundaciones" : "redirect:/login";
    }

    @GetMapping("/adopta")
    public String adopta(Authentication auth) {
        return esFundacion(auth) ? "fundacion/adopta" : "redirect:/login";
    }

    @GetMapping("/apadrina")
    public String apadrina(Authentication auth) {
        return esFundacion(auth) ? "fundacion/apadrina" : "redirect:/login";
    }

    // ... agrega los demás métodos igual
}
