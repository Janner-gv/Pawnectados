package com.example.Pawnectados.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Muestra la vista del login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "principal/login";
    }

    // Redirige la raíz "/" al login
    @GetMapping("/")
    public String redireccionarInicio() {
        return "redirect:/login";
    }

    // Cierra sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // ✅ Borra todos los atributos de sesión
        return "redirect:/login"; // 🔁 Redirige a la vista de login
    }
}
