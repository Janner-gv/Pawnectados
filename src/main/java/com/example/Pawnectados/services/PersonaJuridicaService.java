package com.example.Pawnectados.services;

import com.example.Pawnectados.models.PersonaJuridica;

import java.util.List;
import java.util.Optional;

public interface PersonaJuridicaService {

    PersonaJuridica registrarPersonaJuridica(PersonaJuridica pj);

    Optional<PersonaJuridica> obtenerPorUsuarioId(Long idUsuario);

    Optional<PersonaJuridica> obtenerPorId(Long id);

    PersonaJuridica aprobarPersona(Long id);

    PersonaJuridica rechazarPersona(Long id);

    List<PersonaJuridica> listarAprobadas();

    boolean usuarioTienePersona(Long usuarioId);

    boolean personaAprobada(Long usuarioId);
}
