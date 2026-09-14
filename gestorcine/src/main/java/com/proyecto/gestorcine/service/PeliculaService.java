package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.Pelicula;
import com.proyecto.gestorcine.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {
    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    public List<Pelicula> findAll() { return repository.findAll(); }

    public Pelicula findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada: " + id));
    }

    public Pelicula save(Pelicula pelicula) { return repository.save(pelicula); }

    public void delete(Integer id) { repository.deleteById(id); }
}
