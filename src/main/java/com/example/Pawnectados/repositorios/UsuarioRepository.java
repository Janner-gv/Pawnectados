package com.example.Pawnectados.repositorios;

import org.springframework.stereotype.Repository;
import com.example.Pawnectados.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
