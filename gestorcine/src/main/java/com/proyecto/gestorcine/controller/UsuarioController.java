package com.proyecto.gestorcine.controller;

import com.proyecto.gestorcine.entity.Administrador;
import com.proyecto.gestorcine.entity.Cliente;
import com.proyecto.gestorcine.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente registrarCliente(@Valid @RequestBody Cliente cliente) {
        return service.registrarCliente(cliente);
    }

    @PostMapping("/administradores")
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador registrarAdministrador(@Valid @RequestBody Administrador administrador) {
        return service.registrarAdministrador(administrador);
    }

    @GetMapping("/clientes/{usuario}")
    public Cliente unCliente(@PathVariable String usuario) { return service.findClienteById(usuario); }

    @GetMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam int contrasena) {
        return service.iniciarSesion(usuario, contrasena);
    }
}
