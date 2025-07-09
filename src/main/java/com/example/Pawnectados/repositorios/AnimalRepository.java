package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // ✅ Obtener los animales registrados por un usuario específico
    List<Animal> findByUsuarioId(Long idUsuario);

    // ✅ Gráfica: contar animales registrados por mes
    @Query("SELECT MONTH(a.fechaRegistro), COUNT(a) FROM Animal a GROUP BY MONTH(a.fechaRegistro) ORDER BY MONTH(a.fechaRegistro)")
    List<Object[]> contarAnimalesPorMes();
}
