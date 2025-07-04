package com.example.Pawnectados.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "Dinero", "Objetos", etc.

    private double cantidad; // Valor o cantidad de la donación

    private LocalDate fecha;

    private String descripcion;

    // Asociación con usuario (opcional si quieres saber quién donó)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
