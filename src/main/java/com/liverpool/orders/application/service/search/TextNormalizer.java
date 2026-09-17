package com.liverpool.orders.application.service.search;

/**
 * Interfaz para normalizar textos.
 */
public interface TextNormalizer {

    /**
     * Normaliza un texto.
     *
     * @param text texto a normalizar
     * @return texto normalizado
     */
    String normalize(String text);
}