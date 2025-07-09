package com.example.Pawnectados.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_animal;

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String sexo;
    private String motivo; // Abandono, Enfermedad, etc.
    private String deseaAdoptar; // "Sí" o "No"
    private String apadrinar;    // "Sí" o "No"

    @Column(length = 1000)
    private String descripcion;

    private String imagen;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro = new Date();

    // 🔗 Relación con el usuario que registró
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // 🔗 Relación con la fundación (si aplica)
    @ManyToOne
    @JoinColumn(name = "id_fundacion")
    private Fundacion fundacion;

    // 🔗 Relación con la veterinaria (si aplica)
    @ManyToOne
    @JoinColumn(name = "id_veterinaria")
    private Veterinaria veterinaria;

    // === CONSTRUCTORES ===
    public Animal() {}

    // === GETTERS Y SETTERS ===

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }

    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }

    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }

    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDeseaAdoptar() { return deseaAdoptar; }

    public void setDeseaAdoptar(String deseaAdoptar) { this.deseaAdoptar = deseaAdoptar; }

    public String getApadrinar() { return apadrinar; }

    public void setApadrinar(String apadrinar) { this.apadrinar = apadrinar; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public Date getFechaRegistro() { return fechaRegistro; }

    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Fundacion getFundacion() { return fundacion; }

    public void setFundacion(Fundacion fundacion) { this.fundacion = fundacion; }

    public Veterinaria getVeterinaria() { return veterinaria; }

    public void setVeterinaria(Veterinaria veterinaria) { this.veterinaria = veterinaria; }
}
