package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {

    // Buscar veterinaria por usuarioId usando JPQL
    @Query("SELECT v FROM Veterinaria v WHERE v.usuario.id_usuario = :usuarioId")
    Optional<Veterinaria> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
