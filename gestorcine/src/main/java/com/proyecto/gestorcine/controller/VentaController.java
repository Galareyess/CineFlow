package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Reserva;
import com.proyecto.gestorcine.entity.Ticket;
import com.proyecto.gestorcine.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService service;

    public VentaController(VentaService service) { this.service = service; }

    @GetMapping
    public List<Reserva> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Reserva one(@PathVariable Integer id) { return service.findById(id); }

    @GetMapping("/tickets")
    public List<Ticket> allTickets() { return service.findAllTickets(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva create(@Valid @RequestBody Reserva reserva) { return service.save(reserva); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Integer id) { service.cancelar(id); }
}
