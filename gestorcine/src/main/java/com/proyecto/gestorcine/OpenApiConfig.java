package com.proyecto.gestorcine;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("CineFlow")
                .description("API REST para la gestion de un cine: cartelera, salas, butacas, ventas y kiosco.")
                .version("1.0"));
    }
}
