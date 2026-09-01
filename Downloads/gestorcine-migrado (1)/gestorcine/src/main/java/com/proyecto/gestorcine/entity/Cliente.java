package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cliente: hereda usuario y contrasena de Usuario, y agrega el DNI.
 * Mapea a la tabla "clientes", igual que en el proyecto original.
 */
@Entity
@Table(name = "clientes")
@DiscriminatorValue("CLIENTE")
@PrimaryKeyJoinColumn(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Usuario {

    @NotBlank
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
}
