package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public boolean generarTokenYEnviarCorreo(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (usuario == null) {
            return false;
        }

        // Generar token único
        String token = UUID.randomUUID().toString();
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(30);

        // Guardar en BD
        usuario.setResetToken(token);
        usuario.setTokenExpiration(expiracion);
        usuarioRepository.save(usuario);

        // Crear enlace
        String enlace = "http://localhost:8080/recuperar?token=" + token;

        // Correo
        String asunto = "Recuperación de contraseña - Pawnectados";
        String cuerpo = "Hola " + usuario.getNombre() +
                "\n\nHas solicitado recuperar tu contraseña." +
                "\nHaz clic en este enlace para restablecerla:" +
                "\n" + enlace +
                "\n\nEste enlace expira en 30 minutos.";

        emailService.enviarCorreo(email, asunto, cuerpo);

        return true;
    }
}
