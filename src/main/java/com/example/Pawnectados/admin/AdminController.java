package com.example.Pawnectados.admin;

import com.example.Pawnectados.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private boolean esAdmin(Usuario usuario) {
        return usuario != null && usuario.getRol() == 4;
    }

    @GetMapping("/admin/fundaciones")
    public String verFundaciones(HttpSession session) {
        return esAdmin((Usuario) session.getAttribute("usuario")) ?
                "admin/fundaciones" : "redirect:/login";
    }

    @GetMapping("/admin/veterinarias")
    public String verVeterinarias(HttpSession session) {
        return esAdmin((Usuario) session.getAttribute("usuario")) ?
                "admin/veterinarias" : "redirect:/login";
    }

    @GetMapping("/admin/trabajadores")
    public String verTrabajadores(HttpSession session) {
        return esAdmin((Usuario) session.getAttribute("usuario")) ?
                "admin/Trabajadores" : "redirect:/login";
    }

    @GetMapping("/admin/donaciones")
    public String verDonaciones(HttpSession session) {
        return esAdmin((Usuario) session.getAttribute("usuario")) ?
                "admin/Donaciones" : "redirect:/login";
    }
}
