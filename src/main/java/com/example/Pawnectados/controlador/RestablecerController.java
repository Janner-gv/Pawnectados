package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.PasswordResetToken;
import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.PasswordResetRepository;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class RestablecerController {

    @Autowired
    private PasswordResetRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // -------------------------------------------------------------
    //   MOSTRAR FORMULARIO DE RESTABLECIMIENTO
    // -------------------------------------------------------------
    @GetMapping("/restablecer")
    public String mostrarFormulario(@RequestParam("token") String token, Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null) {
            model.addAttribute("error", "El enlace no es válido.");
            return "principal/restablecer_error";
        }

        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "El enlace ha expirado.");
            return "principal/restablecer_error";
        }

        model.addAttribute("token", token);
        return "principal/restablecer";
    }


    // -------------------------------------------------------------
    //   PROCESAR LA NUEVA CONTRASEÑA
    // -------------------------------------------------------------
    @PostMapping("/restablecer")
    public String procesarRestablecimiento(
            @RequestParam("token") String token,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null) {
            model.addAttribute("error", "El enlace no es válido.");
            return "principal/restablecer_error";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("token", token);
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "principal/restablecer";
        }

        Usuario usuario = usuarioRepository.findByEmail(resetToken.getEmail()).orElse(null);

        if (usuario == null) {
            model.addAttribute("error", "El usuario no existe.");
            return "principal/restablecer_error";
        }

        // Guardar la nueva contraseña
        usuario.setPassword(passwordEncoder.encode(password));
        usuarioRepository.save(usuario);

        // Eliminar token luego de usarlo
        tokenRepository.delete(resetToken);

        return "principal/restablecer_success";
    }
}
