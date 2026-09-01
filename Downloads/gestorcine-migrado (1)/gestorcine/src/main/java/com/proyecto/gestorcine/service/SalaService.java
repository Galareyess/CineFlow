package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.Butaca;
import com.proyecto.gestorcine.entity.ButacaId;
import com.proyecto.gestorcine.entity.Sala;
import com.proyecto.gestorcine.repository.ButacaRepository;
import com.proyecto.gestorcine.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    private final SalaRepository repository;
    private final ButacaRepository butacaRepository;

    public SalaService(SalaRepository repository, ButacaRepository butacaRepository) {
        this.repository = repository;
        this.butacaRepository = butacaRepository;
    }

    public List<Sala> findAll() { return repository.findAll(); }

    public Sala findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sala no encontrada: " + id));
    }

    public Sala save(Sala sala) { return repository.save(sala); }

    public void delete(Integer id) { repository.deleteById(id); }

    // --- Butacas ---

    public List<Butaca> findAllButacas() { return butacaRepository.findAll(); }

    public Butaca saveButaca(Butaca butaca) { return butacaRepository.save(butaca); }

    public void deleteButaca(char fila, int numero) {
        butacaRepository.deleteById(new ButacaId(fila, numero));
    }

    public boolean butacaDisponible(char fila, int numero) {
        return butacaRepository.findById(new ButacaId(fila, numero))
                .map(b -> !b.isEstado())
                .orElse(false);
    }
}
