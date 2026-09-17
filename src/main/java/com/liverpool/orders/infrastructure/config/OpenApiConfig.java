package com.liverpool.orders.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de la documentación OpenAPI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura la información general de la API.
     *
     * @return configuración de OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Liverpool Orders API")
                        .version("1.0")
                        .description(
                                "API para la gestión de clientes, pedidos y entregas."));
    }
}