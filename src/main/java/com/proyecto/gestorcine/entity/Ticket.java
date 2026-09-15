package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_ticket")
    private Integer codigoTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    private Funcion funcion;

    // Butaca tiene clave compuesta (fila, numero), asi que el join
    // tambien necesita dos columnas para reconstruir la relacion.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "butaca_fila", referencedColumnName = "fila"),
            @JoinColumn(name = "butaca_numero", referencedColumnName = "numero")
    })
    private Butaca butaca;

    @Column(name = "precio")
    private int precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    // Sin esto, convertir una Reserva a JSON entra en loop infinito:
    // Reserva -> tickets -> ticket.reserva -> tickets -> ... El cliente
    // ya sabe a que reserva pertenece el ticket por contexto, no hace
    // falta que cada ticket la traiga de vuelta.
    @JsonIgnore
    private Reserva reserva;
}