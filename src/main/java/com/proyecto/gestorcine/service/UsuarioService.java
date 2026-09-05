package com.proyecto.gestorcine.service;

import com.proyecto.gestorcine.entity.Administrador;
import com.proyecto.gestorcine.entity.Cliente;
import com.proyecto.gestorcine.entity.Usuario;
import com.proyecto.gestorcine.repository.AdministradorRepository;
import com.proyecto.gestorcine.repository.ClienteRepository;
import com.proyecto.gestorcine.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final ClienteRepository clienteRepository;
    private final AdministradorRepository administradorRepository;

    public UsuarioService(UsuarioRepository repository, ClienteRepository clienteRepository, AdministradorRepository administradorRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.administradorRepository = administradorRepository;
    }

    public Cliente registrarCliente(Cliente cliente) {
        if (repository.existsById(cliente.getUsuario())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
        }
        return clienteRepository.save(cliente);
    }

    public Administrador registrarAdministrador(Administrador administrador) {
        if (repository.existsById(administrador.getUsuario())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre.");
        }
        return administradorRepository.save(administrador);
    }

    public String iniciarSesion(String usuario, int contrasena) {
        Usuario u = repository.findById(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario o contrasena incorrectos."));
        if (u.getContrasena() != contrasena) {
            throw new IllegalArgumentException("Usuario o contrasena incorrectos.");
        }
        return (u instanceof Administrador) ? "ADMIN" : "CLIENTE";
    }

    public Cliente findClienteById(String usuario) {
        return clienteRepository.findById(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + usuario));
    }
}
