package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    // Manual, sin @GeneratedValue: es el numero fisico de la sala,
    // el mismo que esta pegado en la puerta.
    @Id
    @Column(name = "numero_sala")
    private Integer numeroSala;

    @Positive
    @Column(name = "capacidad")
    private int capacidad;

    // true = llena, false = disponible
    @Column(name = "estado")
    private boolean estado;

    @Column(name = "formato", length = 10)
    private String formato;
}
