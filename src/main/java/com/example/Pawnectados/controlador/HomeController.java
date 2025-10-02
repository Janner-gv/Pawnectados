package com.example.Pawnectados.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String raiz() {
        return "redirect:/Home";
    }

    @GetMapping({"/Home"})
    public String mostrarHome() {
        return "principal/Home"; // template Home.html
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "principal/login"; // template login.html
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/Home";
    }
}
