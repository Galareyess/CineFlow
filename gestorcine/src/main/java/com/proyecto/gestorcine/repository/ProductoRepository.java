package com.proyecto.gestorcine.repository;

import com.proyecto.gestorcine.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
