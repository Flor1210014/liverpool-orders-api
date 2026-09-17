package com.liverpool.orders.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuración del cliente HTTP para MockAPI.
 */
@Configuration
public class RestClientConfig {

    /**
     * Crea el cliente para consumir MockAPI.
     *
     * @param baseUrl URL base del servicio externo
     * @return cliente configurado
     */
    @Bean
    public RestClient mockApiRestClient(
            @Value("${mockapi.base-url}") String baseUrl) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}