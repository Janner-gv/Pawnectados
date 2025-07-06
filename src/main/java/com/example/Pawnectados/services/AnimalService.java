package com.example.Pawnectados.services;

import com.example.Pawnectados.repositorios.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public long contarAnimales() {
        return animalRepository.count();
    }

    public Map<String, Long> obtenerAnimalesPorMes() {
        List<Object[]> resultados = animalRepository.contarAnimalesPorMes();
        Map<String, Long> datos = new LinkedHashMap<>();

        String[] nombresMeses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (Object[] fila : resultados) {
            int mes = ((Integer) fila[0]) - 1;
            Long cantidad = (Long) fila[1];
            datos.put(nombresMeses[mes], cantidad);
        }

        return datos;
    }
}
