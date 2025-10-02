package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.models.Rol;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import com.example.Pawnectados.repositorios.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔹 Registrar usuario
    public String registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return "El correo ya está registrado";
        }

        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Rol rolUsuario = new Rol();
            rolUsuario.setNombre("ROLE_USER");
            usuario.setRoles(Set.of(rolUsuario));
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "Usuario registrado";
    }

    // 🔹 Autenticar usuario
    public Usuario autenticar(String email, String password) {
        Optional<Usuario> optional = usuarioRepository.findByEmail(email);
        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    // 🔹 Obtener todos
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // 🔹 Obtener por ID
    public Usuario obtenerPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario).orElse(null);
    }

    // 🔹 Actualizar usuario
    public boolean actualizarUsuario(Long id_usuario, Usuario datosActualizados) {
        Optional<Usuario> optional = usuarioRepository.findById(id_usuario);
        if (optional.isPresent()) {
            Usuario existente = optional.get();
            existente.setNombre(datosActualizados.getNombre());
            existente.setEmail(datosActualizados.getEmail());
            existente.setTelefono(datosActualizados.getTelefono());
            existente.setDireccion(datosActualizados.getDireccion());
            existente.setRoles(datosActualizados.getRoles());
            usuarioRepository.save(existente);
            return true;
        }
        return false;
    }

    // 🔹 Eliminar usuario
    public boolean eliminarUsuario(Long id_usuario) {
        if (usuarioRepository.existsById(id_usuario)) {
            usuarioRepository.deleteById(id_usuario);
            return true;
        }
        return false;
    }

    // 🔹 Contar usuarios por rol
    public long contarUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getRoles() != null &&
                        u.getRoles().stream()
                                .anyMatch(r -> r.getNombre().equalsIgnoreCase(nombreRol)))
                .count();
    }

    // 🔹 Contar donaciones
    public long contarDonaciones() {
        return donacionRepository.count();
    }

    // 🔹 Obtener correos de usuarios
    public List<String> obtenerCorreosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEmail() != null && !u.getEmail().isBlank())
                .map(Usuario::getEmail)
                .distinct()
                .collect(Collectors.toList());
    }

    // 🔹 Buscar por email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }
}
