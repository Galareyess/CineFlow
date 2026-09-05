package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.dto.DatosTarjeta;
import com.proyecto.gestorcine.entity.Butaca;
import com.proyecto.gestorcine.entity.Cliente;
import com.proyecto.gestorcine.entity.Funcion;
import com.proyecto.gestorcine.entity.Reserva;
import com.proyecto.gestorcine.entity.Ticket;
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
    private final PagoService pagoService;

    public VentaService(ReservaRepository repository, TicketRepository ticketRepository, ButacaRepository butacaRepository,
                         ClienteRepository clienteRepository, FuncionRepository funcionRepository, PagoService pagoService) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.butacaRepository = butacaRepository;
        this.clienteRepository = clienteRepository;
        this.funcionRepository = funcionRepository;
        this.pagoService = pagoService;
    }

    public List<Reserva> findAll() { return repository.findAll(); }

    public Reserva findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + id));
    }

    public List<Ticket> findAllTickets() { return ticketRepository.findAll(); }

    @Transactional
    public Reserva save(Reserva reserva, DatosTarjeta tarjeta) {
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

        Reserva guardada = repository.save(reserva);

        // Marcamos cada butaca como ocupada.
        for (Ticket t : guardada.getTickets()) {
            t.getButaca().ocupar();
            butacaRepository.save(t.getButaca());
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
        repository.delete(reserva);
    }
}
