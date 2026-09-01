package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.Producto;
import com.proyecto.gestorcine.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComboService {
    private final ProductoRepository repository;

    public ComboService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> findAll() { return repository.findAll(); }

    public Producto findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
    }

    public Producto save(Producto producto) { return repository.save(producto); }

    public void delete(Integer id) { repository.deleteById(id); }

    @Transactional
    public void descontarStock(Integer id, int cantidad) {
        Producto producto = findById(id);
        if (producto.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombre());
        }
        producto.setStock(producto.getStock() - cantidad);
    }
}
