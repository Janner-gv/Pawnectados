package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Veterinaria;
import com.example.Pawnectados.repositorios.VeterinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinariaService {

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    public List<Veterinaria> obtenerTodas() {
        return veterinariaRepository.findAll();
    }

    public Veterinaria guardar(Veterinaria veterinaria) {
        return veterinariaRepository.save(veterinaria);
    }

    public Veterinaria obtenerPorId(Integer id) {
        return veterinariaRepository.findById(id).orElse(null);
    }

    public void eliminarPorId(Integer id) {
        veterinariaRepository.deleteById(id);
    }

    public long contarVeterinarias() {
        return veterinariaRepository.count();
    }
}
