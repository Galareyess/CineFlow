package com.proyecto.gestorcine;

import com.proyecto.gestorcine.entity.Administrador;
import com.proyecto.gestorcine.repository.AdministradorRepository;
import com.proyecto.gestorcine.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AdminBootstrap implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;

    @Value("${admin.usuario:admin}")
    private String usuarioPorDefecto;

    @Value("${admin.contrasena:1234}")
    private int contrasenaPorDefecto;

    public AdminBootstrap(UsuarioRepository usuarioRepository, AdministradorRepository administradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.administradorRepository = administradorRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.existsById(usuarioPorDefecto)) {
            System.out.println("Admin por defecto ya existe: " + usuarioPorDefecto);
            return;
        }
        Administrador admin = new Administrador();
        admin.setUsuario(usuarioPorDefecto);
        admin.setContrasena(contrasenaPorDefecto);
        administradorRepository.save(admin);
        System.out.println("Admin por defecto creado: " + usuarioPorDefecto + " / " + contrasenaPorDefecto);
    }
}
