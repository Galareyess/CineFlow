package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Butaca;
import com.proyecto.gestorcine.entity.Sala;
import com.proyecto.gestorcine.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    private final SalaService service;

    public SalaController(SalaService service) { this.service = service; }

    @GetMapping
    public List<Sala> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Sala one(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala create(@Valid @RequestBody Sala sala) { return service.save(sala); }

    @PutMapping("/{id}")
    public Sala update(@PathVariable Integer id, @Valid @RequestBody Sala sala) {
        service.findById(id);
        sala.setNumeroSala(id);
        return service.save(sala);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }

    // --- Butacas ---

    @GetMapping("/butacas")
    public List<Butaca> allButacas() { return service.findAllButacas(); }

    @PostMapping("/butacas")
    @ResponseStatus(HttpStatus.CREATED)
    public Butaca createButaca(@RequestBody Butaca butaca) { return service.saveButaca(butaca); }

    @GetMapping("/butacas/{fila}/{numero}/disponible")
    public boolean disponible(@PathVariable char fila, @PathVariable int numero) {
        return service.butacaDisponible(fila, numero);
    }

    @DeleteMapping("/butacas/{fila}/{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteButaca(@PathVariable char fila, @PathVariable int numero) {
        service.deleteButaca(fila, numero);
    }
}
