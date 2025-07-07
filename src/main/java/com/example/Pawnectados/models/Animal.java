package com.example.Pawnectados.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especie;

    private String raza;

    private int edad;

    private String sexo;

    private String motivo; // Abandono, Enfermedad, etc.

    private String deseaAdoptar; // Sí o No

    private String apadrinar; // Sí o No

    @Column(length = 1000)
    private String descripcion;

    private String imagen; // Nombre del archivo o ruta

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro = new Date();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // === CONSTRUCTORES ===
    public Animal() {}

    // === GETTERS Y SETTERS ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDeseaAdoptar() {
        return deseaAdoptar;
    }

    public void setDeseaAdoptar(String deseaAdoptar) {
        this.deseaAdoptar = deseaAdoptar;
    }

    public String getApadrinar() {
        return apadrinar;
    }

    public void setApadrinar(String apadrinar) {
        this.apadrinar = apadrinar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
