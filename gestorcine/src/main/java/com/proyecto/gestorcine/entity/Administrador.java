package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administradores")
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "usuario")
@NoArgsConstructor
public class Administrador extends Usuario {
}
