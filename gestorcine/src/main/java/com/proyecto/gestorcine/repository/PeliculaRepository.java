package com.proyecto.gestorcine.repository;

import com.proyecto.gestorcine.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}
