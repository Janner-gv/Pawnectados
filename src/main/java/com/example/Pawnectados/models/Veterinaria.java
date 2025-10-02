package com.example.Pawnectados.models;

import jakarta.persistence.*;

@Entity
@Table(name = "veterinaria")
public class Veterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String licencia;
    private String servicios;
    private String horarios;

    // Relación con usuario
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // === Constructores ===
    public Veterinaria() {}

    public Veterinaria(Usuario usuario, String licencia, String servicios, String horarios) {
        this.usuario = usuario;
        this.licencia = licencia;
        this.servicios = servicios;
        this.horarios = horarios;
    }

    // === Getters y Setters ===
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
