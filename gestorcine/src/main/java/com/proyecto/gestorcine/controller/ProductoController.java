package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Producto;
import com.proyecto.gestorcine.service.ComboService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ComboService service;

    public ProductoController(ComboService service) { this.service = service; }

    @GetMapping
    public List<Producto> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Producto one(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@Valid @RequestBody Producto producto) { return service.save(producto); }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Integer id, @Valid @RequestBody Producto producto) {
        service.findById(id);
        producto.setCodigoProducto(id);
        return service.save(producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }

    @PatchMapping("/{id}/stock")
    public void descontarStock(@PathVariable Integer id, @RequestParam int cantidad) {
        service.descontarStock(id, cantidad);
    }
}
