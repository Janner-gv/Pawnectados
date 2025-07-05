package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fundacion")
public class FundacionController {

    /** Rol 3 = Fundación   (ajusta al valor que uses) */
    private boolean esFundacion(Usuario u) {
        return u != null && u.getRol() == 3;
    }

    /** Landing de la fundación —> /fundacion/index */
    @GetMapping("/index")
    public String indexFundacion(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/fundaciones"          // templates/fundacion/index.html
                : "redirect:/login";
    }

    /** Ejemplo de otra pantalla: lista de animales en adopción */
    @GetMapping("/animales")
    public String listarAnimales(HttpSession session) {
        return esFundacion((Usuario) session.getAttribute("usuario"))
                ? "fundacion/animales"       // templates/fundacion/animales.html
                : "redirect:/login";
    }
}
