package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad base para los usuarios del sistema. Usa herencia JOINED: esta
 * clase mapea la tabla "usuarios" (campos comunes) y cada subclase
 * (Cliente, Administrador) tiene su propia tabla, unida por "usuario".
 * La columna discriminadora "tipo" reemplaza al campo Tipo que ya tenia
 * la tabla usuarios original.
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING, length = 20)
@Getter
@Setter
@NoArgsConstructor
public abstract class Usuario {

    @Id
    @NotBlank
    @Column(name = "usuario", length = 50)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private int contrasena;
}
