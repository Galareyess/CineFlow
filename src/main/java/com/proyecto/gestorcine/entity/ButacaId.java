package com.proyecto.gestorcine.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clave primaria compuesta de Butaca: se identifica por (fila, numero),
 * sin un codigo propio, igual que en el proyecto original.
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ButacaId implements Serializable {
    private char fila;
    private int numero;
}
