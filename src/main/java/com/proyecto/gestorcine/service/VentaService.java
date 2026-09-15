package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.dto.DatosTarjeta;
import com.proyecto.gestorcine.entity.Butaca;
import com.proyecto.gestorcine.entity.Cliente;
import com.proyecto.gestorcine.entity.DetalleProducto;
import com.proyecto.gestorcine.entity.Funcion;
import com.proyecto.gestorcine.entity.Producto;
import com.proyecto.gestorcine.entity.Reserva;
import com.proyecto.gestorcine.entity.Ticket;
import com.proyecto.gestorcine.repository.ButacaRepository;
import com.proyecto.gestorcine.repository.ClienteRepository;
import com.proyecto.gestorcine.repository.FuncionRepository;
import com.proyecto.gestorcine.repository.ReservaRepository;
import com.proyecto.gestorcine.repository.TicketRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {
    private static final int PRECIO_ENTRADA = 5000;
    private final ReservaRepository repository;
    private final TicketRepository ticketRepository;
    private final ButacaRepository butacaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionRepository funcionRepository;
    private final PagoService pagoService;
    private final ComboService comboService;

    public VentaService(ReservaRepository repository, TicketRepository ticketRepository, ButacaRepository butacaRepository,
                        ClienteRepository clienteRepository, FuncionRepository funcionRepository, PagoService pagoService,
                        ComboService comboService) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.butacaRepository = butacaRepository;
        this.clienteRepository = clienteRepository;
        this.funcionRepository = funcionRepository;
        this.pagoService = pagoService;
        this.comboService = comboService;
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
            /*El backend establece el precio de forma automatica al estar como constante*/
            t.setPrecio(PRECIO_ENTRADA);
        }

        if (reserva.getDetallesProducto() != null) {
            for (DetalleProducto detalle : reserva.getDetallesProducto()) {
                Producto producto = comboService.findById(detalle.getProducto().getCodigoProducto());
                detalle.setProducto(producto);
                detalle.setPrecioUnitario(producto.getPrecio());
                detalle.setReserva(reserva);
            }
        }

        /*Precio total*/
        BigDecimal total = BigDecimal.ZERO;

        for (Ticket t : reserva.getTickets()) {
            total = total.add(BigDecimal.valueOf(t.getPrecio()));
        }

        if (reserva.getDetallesProducto() != null) {
            for (DetalleProducto detalle : reserva.getDetallesProducto()) {
                BigDecimal subtotal = detalle.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(detalle.getCantidad()));
                total = total.add(subtotal);
            }
        }

        if(!pagoService.procesarPago(tarjeta, total)){
            throw new IllegalArgumentException("Pago rechazado");
        }

        Reserva guardada = repository.save(reserva);

        // Marcamos cada butaca como ocupada.
        for (Ticket t : guardada.getTickets()) {
            t.getButaca().ocupar();
            butacaRepository.save(t.getButaca());
        }

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

        if (reserva.getDetallesProducto() != null) {
            for (DetalleProducto detalle : reserva.getDetallesProducto()) {
                comboService.reponerStock(detalle.getProducto().getCodigoProducto(), detalle.getCantidad());
            }
        }
        repository.delete(reserva);
    }
}