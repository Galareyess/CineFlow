package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "butacas")
@Getter
@Setter
@NoArgsConstructor
public class Butaca {

    @EmbeddedId
    private ButacaId id;

    // true = ocupada, false = libre
    @Column(name = "estado")
    private boolean estado;

    // Constructor de conveniencia: envuelve fila+numero en el ButacaId.
    // No es un simple mapeo campo-a-campo, asi que Lombok no lo puede
    // generar solo con @AllArgsConstructor.
    public Butaca(char fila, int numero, boolean estado) {
        this.id = new ButacaId(fila, numero);
        this.estado = estado;
    }

    public void ocupar() {
        this.estado = true;
    }

    public void liberar() {
        this.estado = false;
    }

    public char getFila() {
        return id.getFila();
    }

    public int getNumero() {
        return id.getNumero();
    }

    @Override
    public String toString() {
        return id.getFila() + "-" + id.getNumero();
    }
}
