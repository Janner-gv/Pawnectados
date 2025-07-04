package com.example.Pawnectados.repositorios;

import com.example.Pawnectados.models.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    long count(); // para contar todas las donaciones

}
