package com.example.Pawnectados.controlador;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.models.Rol;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import com.example.Pawnectados.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AuthoController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String email,
                                     @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();
        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            if (passwordEncoder.matches(password, u.getPassword())) {
                response.put("status", "success");
                response.put("message", "¡Login exitoso!");
                if (u.getRoles().stream().anyMatch(r -> r.getNombre().equals("ROLE_ADMIN")))
                    response.put("rol", "ROLE_ADMIN");
                else if (u.getRoles().stream().anyMatch(r -> r.getNombre().equals("ROLE_FUNDACION")))
                    response.put("rol", "ROLE_FUNDACION");
                else if (u.getRoles().stream().anyMatch(r -> r.getNombre().equals("ROLE_VETERINARIA")))
                    response.put("rol", "ROLE_VETERINARIA");
                else
                    response.put("rol", "ROLE_USER");
            } else {
                response.put("status", "error");
                response.put("message", "Contraseña incorrecta.");
            }
        } else {
            response.put("status", "error");
            response.put("message", "Usuario no encontrado.");
        }

        return response;
    }

    // ================= REGISTRO =================
    @PostMapping("/registro")
    public Map<String, Object> registrar(@RequestParam String nombre,
                                         @RequestParam String email,
                                         @RequestParam String password,
                                         @RequestParam String telefono) {
        Map<String, Object> response = new HashMap<>();

        if (usuarioRepo.existsByEmail(email)) {
            response.put("status", "error");
            response.put("message", "El correo ya está registrado.");
            return response;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setPassword(passwordEncoder.encode(password));

        // Asignar rol USER por defecto
        Rol rolUser = rolRepo.findByNombre("ROLE_USER").orElseGet(() -> {
            Rol nuevo = new Rol();
            nuevo.setNombre("ROLE_USER");
            return rolRepo.save(nuevo);
        });
        u.setRoles(Set.of(rolUser));

        usuarioRepo.save(u);

        response.put("status", "success");
        response.put("message", "Usuario registrado correctamente.");
        return response;
    }
}
