package com.example.Pawnectados.services;

import com.example.Pawnectados.models.EstadoVerificacion;
import com.example.Pawnectados.models.Fundacion;
import com.example.Pawnectados.models.Usuario;
import com.example.Pawnectados.repositorios.FundacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundacionService {

    @Autowired
    private FundacionRepository fundacionRepo;

    // Registrar fundación con todos los campos
    public Fundacion registrarFundacion(Usuario u, String nombre, String direccion, String telefono, String mision) {
        Fundacion f = new Fundacion();
        f.setUsuario(u);
        f.setNombre(nombre);
        f.setDireccion(direccion);
        f.setTelefono(telefono);
        f.setMision(mision != null ? mision : "");
        f.setEstadoVerificacion(EstadoVerificacion.PENDIENTE);
        return fundacionRepo.save(f);
    }

    // Obtener por id de usuario
    public Optional<Fundacion> obtenerPorUsuarioId(Long usuarioId) {
        return fundacionRepo.findByUsuarioId(usuarioId);
    }

    // Aprobar fundación
    public Fundacion aprobarFundacion(Long idFundacion) {
        Fundacion f = fundacionRepo.findById(idFundacion)
                .orElseThrow(() -> new RuntimeException("Fundación no encontrada"));
        f.setEstadoVerificacion(EstadoVerificacion.APROBADO);
        return fundacionRepo.save(f);
    }

    // Rechazar fundación
    public Fundacion rechazarFundacion(Long idFundacion) {
        Fundacion f = fundacionRepo.findById(idFundacion)
                .orElseThrow(() -> new RuntimeException("Fundación no encontrada"));
        f.setEstadoVerificacion(EstadoVerificacion.RECHAZADO);
        return fundacionRepo.save(f);
    }

    // Listar aprobadas
    public List<Fundacion> listarAprobadas() {
        return fundacionRepo.findAll()
                .stream()
                .filter(f -> f.getEstadoVerificacion() == EstadoVerificacion.APROBADO)
                .toList();
    }

    // Verificar si usuario tiene fundación
    public boolean usuarioTieneFundacion(Long usuarioId) {
        return fundacionRepo.findByUsuarioId(usuarioId).isPresent();
    }

    // Verificar si está aprobada
    public boolean fundacionAprobada(Long usuarioId) {
        Optional<Fundacion> f = fundacionRepo.findByUsuarioId(usuarioId);
        return f.isPresent() && f.get().getEstadoVerificacion() == EstadoVerificacion.APROBADO;
    }
}
