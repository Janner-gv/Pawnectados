package com.example.Pawnectados.models;

import jakarta.persistence.*;

@Entity
@Table(name = "veterinaria")
public class Veterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreClinica;
    private String direccion;
    private String telefono;
    private String licencia;
    private String servicios;
    private String horarios;

    @Enumerated(EnumType.STRING)
    private EstadoVerificacion estadoVerificacion = EstadoVerificacion.PENDIENTE;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Veterinaria() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreClinica() { return nombreClinica; }
    public void setNombreClinica(String nombreClinica) { this.nombreClinica = nombreClinica; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public String getServicios() { return servicios; }
    public void setServicios(String servicios) { this.servicios = servicios; }

    public String getHorarios() { return horarios; }
    public void setHorarios(String horarios) { this.horarios = horarios; }

    public EstadoVerificacion getEstadoVerificacion() { return estadoVerificacion; }
    public void setEstadoVerificacion(EstadoVerificacion estadoVerificacion) { this.estadoVerificacion = estadoVerificacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
