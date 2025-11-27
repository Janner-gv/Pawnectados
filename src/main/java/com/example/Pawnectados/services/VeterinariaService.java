package com.example.Pawnectados.services;

import com.example.Pawnectados.models.EstadoVerificacion;
import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.models.Veterinaria;
import com.example.Pawnectados.repositorios.VeterinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinariaService {

    @Autowired
    private VeterinariaRepository vetRepo;

    // Registrar veterinaria con todos los campos del formulario
    public Veterinaria registrarVeterinaria(
            Usuario u,
            String nombreClinica,
            String direccion,
            String telefono,
            String licencia,
            String servicios,
            String horarios
    ) {
        Veterinaria v = new Veterinaria();
        v.setUsuario(u);
        v.setNombreClinica(nombreClinica != null ? nombreClinica : "");
        v.setDireccion(direccion != null ? direccion : "");
        v.setTelefono(telefono != null ? telefono : "");
        v.setLicencia(licencia != null ? licencia : "");
        v.setServicios(servicios != null ? servicios : "");
        v.setHorarios(horarios != null ? horarios : "");
        v.setEstadoVerificacion(EstadoVerificacion.PENDIENTE);
        return vetRepo.save(v);
    }

    // Obtener por id de usuario
    public Optional<Veterinaria> obtenerPorUsuarioId(Long usuarioId) {
        return vetRepo.findByUsuarioId(usuarioId);
    }

    // Aprobar veterinaria
    public Veterinaria aprobarVeterinaria(Long idVet) {
        Veterinaria v = vetRepo.findById(idVet)
                .orElseThrow(() -> new RuntimeException("Veterinaria no encontrada"));
        v.setEstadoVerificacion(EstadoVerificacion.APROBADO);
        return vetRepo.save(v);
    }

    // Rechazar veterinaria
    public Veterinaria rechazarVeterinaria(Long idVet) {
        Veterinaria v = vetRepo.findById(idVet)
                .orElseThrow(() -> new RuntimeException("Veterinaria no encontrada"));
        v.setEstadoVerificacion(EstadoVerificacion.RECHAZADO);
        return vetRepo.save(v);
    }

    // Listar aprobadas
    public List<Veterinaria> listarAprobadas() {
        return vetRepo.findAll()
                .stream()
                .filter(v -> v.getEstadoVerificacion() == EstadoVerificacion.APROBADO)
                .toList();
    }

    // Verificar si usuario tiene veterinaria
    public boolean usuarioTieneVeterinaria(Long usuarioId) {
        return vetRepo.findByUsuarioId(usuarioId).isPresent();
    }

    // Verificar si está aprobada
    public boolean veterinariaAprobada(Long usuarioId) {
        Optional<Veterinaria> v = vetRepo.findByUsuarioId(usuarioId);
        return v.isPresent() && v.get().getEstadoVerificacion() == EstadoVerificacion.APROBADO;
    }
}
