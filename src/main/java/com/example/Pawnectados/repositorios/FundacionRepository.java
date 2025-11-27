package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Fundacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundacionRepository extends JpaRepository<Fundacion, Long> {

    // Buscar fundación por usuarioId usando JPQL
    @Query("SELECT f FROM Fundacion f WHERE f.usuario.id_usuario = :usuarioId")
    Optional<Fundacion> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
