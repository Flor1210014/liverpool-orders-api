package com.liverpool.orders.application.service.search;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

/**
 * Normaliza textos eliminando acentos y caracteres especiales.
 */
@Component
public class AccentTextNormalizer implements TextNormalizer {

    /**
     * Normaliza un texto para facilitar las búsquedas.
     *
     * @param text texto a normalizar
     * @return texto sin acentos y en minúsculas
     */
    @Override
    public String normalize(String text) {

        if (text == null) {
            return "";
        }

        String normalized = Normalizer
                .normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return normalized
                .toLowerCase()
                .replaceAll("[^a-z0-9]", " ");
    }
}