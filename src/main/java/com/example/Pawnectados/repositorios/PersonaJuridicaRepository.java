package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.PersonaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonaJuridicaRepository extends JpaRepository<PersonaJuridica, Long> {

    @Query("SELECT pj FROM PersonaJuridica pj WHERE pj.usuario.id_usuario = :idUsuario")
    Optional<PersonaJuridica> findByUsuarioId(@Param("idUsuario") Long idUsuario);

}
