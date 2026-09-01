package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pelicula")
    private Integer codigoPelicula;

    @NotBlank
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    // Genero como numero (id de un catalogo de generos), igual que el original.
    @Column(name = "genero")
    private int genero;

    @Positive
    @Column(name = "duracion")
    private int duracion;

    @Column(name = "director", length = 100)
    private String director;

    @Column(name = "actores_principales", length = 255)
    private String actoresPrincipales;

    @Column(name = "sinopsis", length = 2000)
    private String sinopsis;
}
