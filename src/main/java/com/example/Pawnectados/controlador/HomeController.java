
package com.example.Pawnectados.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String mostrarLogin() {

        return "usuario/login";
    }

    @GetMapping("/")
    public String redireccionarInicio() {
        return "redirect:/login";
    }
}
