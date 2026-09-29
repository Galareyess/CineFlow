package com.proyecto.gestorcine.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Clave primaria compuesta de Butaca: se identifica por (fila, numero),
 * sin un codigo propio, igual que en el proyecto original.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ButacaId implements Serializable {
    private char fila;
    private int numero;
}
