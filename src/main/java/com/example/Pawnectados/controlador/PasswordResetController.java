package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.PasswordResetToken;
import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.PasswordResetRepository;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import com.example.Pawnectados.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/principal")
public class PasswordResetController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordResetRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    // ---------------------------------------------------------
    // 1️⃣ MOSTRAR EL FORMULARIO recuperar.html
    // ---------------------------------------------------------
    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperar() {
        return "principal/recuperar";  // archivo recuperar.html
    }

    // ---------------------------------------------------------
    // 2️⃣ PROCESAR FORMULARIO Y ENVIAR CORREO
    // ---------------------------------------------------------
    @PostMapping("/recuperar")
    public String enviarCorreoRecuperacion(@RequestParam String email) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        // Si NO existe el correo
        if (usuario == null) {
            return "redirect:/principal/recuperar?error=correoNoExiste";
        }

        // Token único para el link
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                email,
                LocalDateTime.now().plusMinutes(30)
        );

        tokenRepository.save(resetToken);

        // Link que se enviará al correo
        String link = "http://localhost:8080/restablecer?token=" + token;

        // Enviar el email
        emailService.enviarCorreo(
                email,
                "Recuperación de contraseña",
                "Haz clic en el siguiente enlace para restablecer tu contraseña:\n\n"
                        + link +
                        "\n\nEste enlace expira en 30 minutos."
        );

        // Aquí ya NO devolvemos texto → redirigimos a una vista bonita
        return "redirect:/principal/recuperar/enviado";
    }

    // ---------------------------------------------------------
    // 3️⃣ PANTALLA BONITA: “Correo enviado”
    // ---------------------------------------------------------
    @GetMapping("/recuperar/enviado")
    public String mostrarPaginaEnviado() {
        return "principal/enviado";  // archivo recuperar-enviado.html
    }
}
