package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

    public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT MONTH(a.fechaRegistro) AS mes, COUNT(a) AS cantidad FROM Animal a GROUP BY MONTH(a.fechaRegistro) ORDER BY mes")
    List<Object[]> contarAnimalesPorMes();

    long count();
}
