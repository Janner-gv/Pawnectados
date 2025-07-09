package com.example.Pawnectados.services;

import com.example.Pawnectados.models.Animal;
import com.example.Pawnectados.repositorios.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public String guardarAnimal(Animal animal, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String nombreArchivo = file.getOriginalFilename();
                animal.setImagen(nombreArchivo);
                // Puedes guardar la imagen en disco si lo deseas
            }
            animalRepository.save(animal);
            return "Animal registrado con éxito";
        } catch (Exception e) {
            return "Error al registrar el animal: " + e.getMessage();
        }
    }

    public List<Animal> obtenerPorUsuario(Long idUsuario) {
        return animalRepository.findByUsuarioId(idUsuario);
    }

    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public long contarAnimales() {
        return animalRepository.count();
    }

    // ✅ Gráfica: Animales registrados por mes
    public Map<String, Long> obtenerAnimalesPorMes() {
        List<Object[]> resultados = animalRepository.contarAnimalesPorMes();
        Map<String, Long> datos = new LinkedHashMap<>();

        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (Object[] fila : resultados) {
            int numeroMes = ((Number) fila[0]).intValue() - 1;
            long cantidad = ((Number) fila[1]).longValue();
            datos.put(meses[numeroMes], cantidad);
        }

        return datos;
    }
}
