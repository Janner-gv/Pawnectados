package com.example.Pawnectados.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donaciones")
@Getter
@Setter
public class Donaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion") // Coincide con la tabla
    private Long idDonacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // Coincide con la tabla
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_fundacion") // Coincide con la tabla
    private Fundacion fundacion;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo")
    private String tipo; // "Dinero", "Objetos", etc.
}
