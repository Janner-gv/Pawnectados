
package com.example.Pawnectados;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "/login";
    }

    @GetMapping("/")
    public String redireccionarInicio() {
        return "redirect:/login";
    }
}
