package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectionController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/redirect")
    public String redirectAfterLogin(Authentication auth, HttpSession session) {
        if (auth == null) {
            return "redirect:/login";
        }

        // Buscar usuario real en la BD por email/username
        Usuario usuario = usuarioRepository.findByEmail(auth.getName()).orElse(null);

        // Guardar en sesión
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        }

        // Redirección por rol
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/Dashboard";  // minúscula
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FUNDACION"))) {
            return "redirect:/fundacion/fundaciones";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_VETERINARIA"))) {
            return "redirect:/veterinaria/veterinaria";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/usuarios/usuarios";
        } else {
            return "redirect:/login";
        }
    }
}
