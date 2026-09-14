package com.proyecto.gestorcine.repository;

import com.proyecto.gestorcine.entity.Butaca;
import com.proyecto.gestorcine.entity.ButacaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ButacaRepository extends JpaRepository<Butaca, ButacaId> {
}
