package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Cambiado para coincidir con id_usuario
    @Query("SELECT a FROM Animal a WHERE a.usuario.id_usuario = :idUsuario")
    List<Animal> findByUsuarioId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT MONTH(a.fechaRegistro), COUNT(a) FROM Animal a GROUP BY MONTH(a.fechaRegistro)")
    List<Object[]> contarAnimalesPorMes();
}
