package com.proyecto.gestorcine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_reserva")
    private Integer codigoReserva;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_usuario", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    // Constructor manual a proposito: no usamos @AllArgsConstructor aca
    // porque incluiria "tickets" como parametro, y queremos que siempre
    // arranque en una lista vacia (no que la pise quien la instancie).
    public Reserva(Integer codigoReserva, Cliente cliente, LocalDateTime fechaCompra) {
        this.codigoReserva = codigoReserva;
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
    }

    /**
     * Agrega un ticket a la reserva y setea la relacion inversa, para que
     * ambos lados del vinculo bidireccional queden consistentes en memoria
     * antes de guardar.
     */
    public void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setReserva(this);
    }
}
