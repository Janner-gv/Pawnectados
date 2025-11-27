package com.example.Pawnectados.models;

import jakarta.persistence.*;

@Entity
@Table(name = "persona_juridica")
public class PersonaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razonSocial;
    private String nit;
    private String direccion;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private EstadoVerificacion estadoVerificacion = EstadoVerificacion.PENDIENTE;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public PersonaJuridica() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public EstadoVerificacion getEstadoVerificacion() { return estadoVerificacion; }
    public void setEstadoVerificacion(EstadoVerificacion estadoVerificacion) { this.estadoVerificacion = estadoVerificacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
