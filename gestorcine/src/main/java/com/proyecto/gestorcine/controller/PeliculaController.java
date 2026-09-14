package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Pelicula;
import com.proyecto.gestorcine.service.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    private final PeliculaService service;

    public PeliculaController(PeliculaService service) { this.service = service; }

    @GetMapping
    public List<Pelicula> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula create(@Valid @RequestBody Pelicula pelicula) { return service.save(pelicula); }

    @PutMapping("/{id}")
    public Pelicula update(@PathVariable Integer id, @Valid @RequestBody Pelicula pelicula) {
        service.findById(id);
        pelicula.setCodigoPelicula(id);
        return service.save(pelicula);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
