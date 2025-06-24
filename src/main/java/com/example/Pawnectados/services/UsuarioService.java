package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return "El correo ya esta registrado";
        }

        usuarioRepository.save(usuario);
        return "Usuario Registrado";
    }

    public Usuario autenticar(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }
}
