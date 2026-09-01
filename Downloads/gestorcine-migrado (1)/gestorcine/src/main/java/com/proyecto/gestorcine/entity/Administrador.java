package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

/**
 * Administrador: no tiene campos propios ademas de los de Usuario, tal
 * como en el proyecto original. Igual mapea a su propia tabla
 * "administradores" (queda practicamente vacia, solo con la clave
 * primaria) porque asi funciona la estrategia de herencia JOINED.
 */
@Entity
@Table(name = "administradores")
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "usuario")
@NoArgsConstructor
public class Administrador extends Usuario {
}
