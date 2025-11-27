package com.example.Pawnectados.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "fundacion")
public class Fundacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String mision;
    private String paginaWeb;

    @Enumerated(EnumType.STRING)
    private EstadoVerificacion estadoVerificacion = EstadoVerificacion.PENDIENTE;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "fundacion")
    private List<Animal> animales;

    public Fundacion() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getMision() { return mision; }
    public void setMision(String mision) { this.mision = mision; }

    public String getPaginaWeb() { return paginaWeb; }
    public void setPaginaWeb(String paginaWeb) { this.paginaWeb = paginaWeb; }

    public EstadoVerificacion getEstadoVerificacion() { return estadoVerificacion; }
    public void setEstadoVerificacion(EstadoVerificacion estadoVerificacion) { this.estadoVerificacion = estadoVerificacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Animal> getAnimales() { return animales; }
    public void setAnimales(List<Animal> animales) { this.animales = animales; }
}
