package com.upiiz.suppliers.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Suppliers",
                description = "Descripción y contacto del alumno que realizó el proyecto",
                version = "1.0.0",
                contact = @Contact(
                        name = "Salvador Trejo Martínez",
                        email = "strejom1800@alumno.ipn.mx",
                        url = "http://localhost:8081/contacto"
                ),
                license = @License(),
                termsOfService = "Hola profe"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Servidor de Produccion",
                        url = "https://stm-proyectofinal-suppliers.onrender.com"
                )
        },
        tags = {
                @Tag(
                        name = "Suppliers",
                        description = "Endpoints para Suppliers"
                )
        }
)
public class OpenApiConfiguration {

}
