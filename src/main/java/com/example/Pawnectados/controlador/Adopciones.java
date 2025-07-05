package com.example.Pawnectados.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Adopciones {

    @GetMapping("/adopcion")
    public String mostrarVistaAdopcion() {
        return "adopcion/Adopcion"; // corresponde a: templates/adopcion/Adopciones.html
    }
}
