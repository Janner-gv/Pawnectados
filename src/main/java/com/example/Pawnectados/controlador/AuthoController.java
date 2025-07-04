package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session) {
        Usuario usuario = usuarioService.autenticar(email, password);
        Map<String, Object> res = new HashMap<>();

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            res.put("status", "success");
            res.put("rol", usuario.getRol());
            res.put("message", "Bienvenido" + usuario.getNombre());
        }else {
            res.put("status", "error");
            res.put("message", "Credenciales invalidas");
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@ModelAttribute Usuario usuario) {
        // Forzar rol de usuario normal
        usuario.setRol(1);  // 1 = Usuario común (persona natural)

        String mensaje = usuarioService.registrarUsuario(usuario);
        Map<String, Object> res = new HashMap<>();
        res.put("status", mensaje.equals("Usuario registrado") ? "success" : "error");
        res.put("message", mensaje);
        return ResponseEntity.ok(res);
    }}
