package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.Funcion;
import com.proyecto.gestorcine.entity.Pelicula;
import com.proyecto.gestorcine.entity.Sala;
import com.proyecto.gestorcine.repository.FuncionRepository;
import com.proyecto.gestorcine.repository.PeliculaRepository;
import com.proyecto.gestorcine.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarteleraService {
    private final FuncionRepository repository;
    private final PeliculaRepository peliculaRepository;
    private final SalaRepository salaRepository;

    public CarteleraService(FuncionRepository repository, PeliculaRepository peliculaRepository, SalaRepository salaRepository) {
        this.repository = repository;
        this.peliculaRepository = peliculaRepository;
        this.salaRepository = salaRepository;
    }

    public List<Funcion> findAll() { return repository.findAll(); }

    public Funcion findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcion no encontrada: " + id));
    }

    public Funcion save(Funcion funcion) {
        // El cliente solo manda el id dentro de pelicula/sala (ej. {"codigoPelicula":5}),
        // asi que resolvemos las entidades reales antes de guardar.
        Pelicula pelicula = peliculaRepository.findById(funcion.getPelicula().getCodigoPelicula())
                .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada."));
        Sala sala = salaRepository.findById(funcion.getSala().getNumeroSala())
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada."));
        funcion.setPelicula(pelicula);
        funcion.setSala(sala);

        if (funcion.getHorario().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede programar una funcion en una fecha/hora pasada.");
        }
        return repository.save(funcion);
    }

    public void delete(Integer id) { repository.deleteById(id); }
}
