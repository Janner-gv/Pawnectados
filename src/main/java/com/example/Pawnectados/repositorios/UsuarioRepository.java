package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM Usuario u JOIN u.roles r WHERE r.id = :rolId")
    long countByRolId(@Param("rolId") Long rolId);

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.id = :rolId")
    List<Usuario> findAllByRolId(@Param("rolId") Long rolId);
}
