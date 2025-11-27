package com.example.Pawnectados.services;

import com.example.Pawnectados.models.PersonaJuridica;
import com.example.Pawnectados.models.EstadoVerificacion;
import com.example.Pawnectados.repositorios.PersonaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService {

    @Autowired
    private PersonaJuridicaRepository personaRepo;

    @Override
    public PersonaJuridica registrarPersonaJuridica(PersonaJuridica pj) {
        if (pj.getEstadoVerificacion() == null) {
            pj.setEstadoVerificacion(EstadoVerificacion.PENDIENTE);
        }
        return personaRepo.save(pj);
    }

    @Override
    public Optional<PersonaJuridica> obtenerPorUsuarioId(Long usuarioId) {
        return personaRepo.findByUsuarioId(usuarioId);
    }

    @Override
    public Optional<PersonaJuridica> obtenerPorId(Long id) {
        return personaRepo.findById(id);
    }

    @Override
    public PersonaJuridica aprobarPersona(Long id) {
        PersonaJuridica pj = personaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona jurídica no encontrada"));
        pj.setEstadoVerificacion(EstadoVerificacion.APROBADO);
        return personaRepo.save(pj);
    }

    @Override
    public PersonaJuridica rechazarPersona(Long id) {
        PersonaJuridica pj = personaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona jurídica no encontrada"));
        pj.setEstadoVerificacion(EstadoVerificacion.RECHAZADO);
        return personaRepo.save(pj);
    }

    @Override
    public List<PersonaJuridica> listarAprobadas() {
        return personaRepo.findAll()
                .stream()
                .filter(p -> p.getEstadoVerificacion() == EstadoVerificacion.APROBADO)
                .toList();
    }

    @Override
    public boolean usuarioTienePersona(Long usuarioId) {
        return personaRepo.findByUsuarioId(usuarioId).isPresent();
    }

    @Override
    public boolean personaAprobada(Long usuarioId) {
        Optional<PersonaJuridica> pj = personaRepo.findByUsuarioId(usuarioId);
        return pj.isPresent() && pj.get().getEstadoVerificacion() == EstadoVerificacion.APROBADO;
    }
}
