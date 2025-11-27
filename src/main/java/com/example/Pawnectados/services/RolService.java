package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Rol;
import com.example.Pawnectados.repositorios.RolRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol obtenerRolPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("El rol no existe: " + nombre));
    }

    public boolean existeRol(String nombre) {
        return rolRepository.findByNombre(nombre).isPresent();
    }

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
}
