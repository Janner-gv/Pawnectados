package com.example.Pawnectados.models;

import jakarta.persistence.*;

@Entity
@Table(name = "fundacion")
public class Fundacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mision;
    private String nit;
    private String paginaWeb;

    // Relación con usuario
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // === Constructores ===
    public Fundacion() {}

    public Fundacion(Usuario usuario, String mision, String nit, String paginaWeb) {
        this.usuario = usuario;
        this.mision = mision;
        this.nit = nit;
        this.paginaWeb = paginaWeb;
    }

    // === Getters y Setters ===
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
