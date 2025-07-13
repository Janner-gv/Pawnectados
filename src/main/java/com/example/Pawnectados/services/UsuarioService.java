package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.DonacionRepository;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return "El correo ya está registrado";
        }
        if (usuario.getRol() == 0) {
            usuario.setRol(1); // por defecto, usuario natural
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "Usuario registrado";
    }

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

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public boolean actualizarUsuario(Long id, Usuario datosActualizados) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario existente = optional.get();
            existente.setNombre(datosActualizados.getNombre());
            existente.setEmail(datosActualizados.getEmail());
            existente.setTelefono(datosActualizados.getTelefono());
            existente.setDireccion(datosActualizados.getDireccion());
            existente.setRol(datosActualizados.getRol());
            usuarioRepository.save(existente);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long contarUsuariosPorRol(int rol) {
        return usuarioRepository.countByRol(rol);
    }

    public long contarDonaciones() {
        return donacionRepository.count();
    }
}
