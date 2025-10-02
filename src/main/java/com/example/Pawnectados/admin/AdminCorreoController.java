package com.example.Pawnectados.admin;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.services.CorreoMasivoService;
import com.example.Pawnectados.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/correos")
public class AdminCorreoController {

    @Autowired
    private CorreoMasivoService correoMasivoService;

    @Autowired
    private UsuarioService usuarioService;

    private boolean esAdmin(Usuario usuario) {
        return usuario != null &&
                usuario.getRoles().stream()
                        .anyMatch(r -> r.getNombre().equals("ROLE_ADMIN"));
    }

    // 🟢 Formulario para redactar correos
    @GetMapping("/redactar")
    public String mostrarFormulario(HttpSession session) {
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(admin)) return "redirect:/login";

        return "admin/RedactarCorreo";
    }

    // 🟢 Procesar envío de correos
    @PostMapping("/enviar")
    public String enviarCorreos(@RequestParam String asunto,
                                @RequestParam String mensaje,
                                HttpSession session) {
        Usuario admin = (Usuario) session.getAttribute("usuario");
        if (!esAdmin(admin)) return "redirect:/login";

        List<String> destinatarios = usuarioService.obtenerCorreosUsuarios();

        if (destinatarios.isEmpty()) {
            return "redirect:/admin/correos/redactar?vacio";
        }

        try {
            correoMasivoService.enviarMasivo(destinatarios, asunto, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/correos/redactar?error";
        }

        return "redirect:/admin/correos/redactar?exito";
    }
}
