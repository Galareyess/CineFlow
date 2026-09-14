package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.*;
import com.proyecto.gestorcine.repository.ButacaRepository;
import com.proyecto.gestorcine.repository.ClienteRepository;
import com.proyecto.gestorcine.repository.FuncionRepository;
import com.proyecto.gestorcine.repository.ReservaRepository;
import com.proyecto.gestorcine.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class VentaService {
    private final ReservaRepository repository;
    private final TicketRepository ticketRepository;
    private final ButacaRepository butacaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionRepository funcionRepository;
    private final ComboService comboService;

    public VentaService(ReservaRepository repository, TicketRepository ticketRepository, ButacaRepository butacaRepository,
                         ClienteRepository clienteRepository, FuncionRepository funcionRepository, ComboService comboService) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.butacaRepository = butacaRepository;
        this.clienteRepository = clienteRepository;
        this.funcionRepository = funcionRepository;
        this.comboService = comboService;
    }

    public List<Reserva> findAll() { return repository.findAll(); }

    public Reserva findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + id));
    }

    public List<Ticket> findAllTickets() { return ticketRepository.findAll(); }

    @Transactional
    public Reserva save(Reserva reserva) {
        // El cliente solo manda el usuario dentro de cliente (ej. {"usuario":"juan"}),
        // asi que resolvemos la entidad real antes de guardar.
        Cliente cliente = clienteRepository.findById(reserva.getCliente().getUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));
        reserva.setCliente(cliente);

        if (reserva.getTickets() == null || reserva.getTickets().isEmpty()) {
            throw new IllegalArgumentException("La reserva necesita al menos un ticket (butaca).");
        }

        // Resolvemos Funcion y Butaca reales de cada ticket, y verificamos
        // que todas las butacas esten libres antes de reservar ninguna.
        for (Ticket t : reserva.getTickets()) {
            Funcion funcion = funcionRepository.findById(t.getFuncion().getCodigoFuncion())
                    .orElseThrow(() -> new IllegalArgumentException("Funcion no encontrada."));
            Butaca butaca = butacaRepository.findById(t.getButaca().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Butaca no encontrada."));
            if (butaca.isEstado()) {
                throw new IllegalArgumentException("La butaca " + butaca + " ya esta ocupada.");
            }
            t.setFuncion(funcion);
            t.setButaca(butaca);
            t.setReserva(reserva);
        }

        // NUEVO: si la reserva incluye productos del kiosco, resolvemos
        // el Producto real de cada uno antes de guardar (mismo criterio
        // que ya usamos con Funcion/Butaca arriba).
        if (reserva.getDetallesProducto() != null) {
            for (DetalleProducto detalle : reserva.getDetallesProducto()) {
                Producto producto = comboService.findById(detalle.getProducto().getCodigoProducto());
                detalle.setProducto(producto);
                detalle.setPrecioUnitario(producto.getPrecio());
                detalle.setReserva(reserva);
            }
        }

        Reserva guardada = repository.save(reserva);

        // Marcamos cada butaca como ocupada.
        for (Ticket t : guardada.getTickets()) {
            t.getButaca().ocupar();
            butacaRepository.save(t.getButaca());
        }

    // NUEVO: descontamos el stock de cada producto vendido. La
    // validacion de "hay stock suficiente" ya vive dentro de
    // ComboService.descontarStock(), no la repetimos aca.
        if (guardada.getDetallesProducto() != null) {
        for (DetalleProducto detalle : guardada.getDetallesProducto()) {
            comboService.descontarStock(detalle.getProducto().getCodigoProducto(), detalle.getCantidad());
        }
    }
        return guardada;
}

    @Transactional
    public void cancelar(Integer id) {
        Reserva reserva = findById(id);
        for (Ticket t : reserva.getTickets()) {
            Butaca butaca = t.getButaca();
            butaca.liberar();
            butacaRepository.save(butaca);
        }

        // NUEVO: reponemos el stock de los productos de esta reserva.
        if (reserva.getDetallesProducto() != null) {
            for (DetalleProducto detalle : reserva.getDetallesProducto()) {
                comboService.reponerStock(detalle.getProducto().getCodigoProducto(), detalle.getCantidad());
            }
        }
        repository.delete(reserva);
    }
}
