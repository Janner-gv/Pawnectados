package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionRepository extends JpaRepository<Donaciones, Long> {

    long count(); // para contar todas las donaciones

}
