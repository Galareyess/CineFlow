package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Funcion;
import com.proyecto.gestorcine.service.CarteleraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartelera")
public class CarteleraController {
    private final CarteleraService service;

    public CarteleraController(CarteleraService service) { this.service = service; }

    @GetMapping
    public List<Funcion> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Funcion one(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcion create(@Valid @RequestBody Funcion funcion) { return service.save(funcion); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
