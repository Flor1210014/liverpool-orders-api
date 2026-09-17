package com.liverpool.orders.application.service.search;

import org.springframework.stereotype.Component;

/**
 * Permite realizar búsquedas considerando errores de escritura.
 */
@Component
public class FuzzyTextMatcher {

    /**
     * Verifica si un texto coincide con una búsqueda.
     *
     * @param text texto donde se realiza la búsqueda
     * @param query texto que se busca
     * @return true si existe coincidencia
     */
    public boolean matches(String text, String query) {

        if (text == null || query == null) {
            return false;
        }

        if (text.contains(query)) {
            return true;
        }

        String[] words = text.split("\\s+");

        for (String word : words) {

            if (matchesWord(word, query)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Compara una palabra con el texto buscado.
     *
     * @param word palabra a comparar
     * @param query texto buscado
     * @return true si la distancia permitida coincide
     */
    private boolean matchesWord(String word, String query) {

        int maxDistance = calculateMaxDistance(query.length());

        return levenshteinDistance(word, query) <= maxDistance;
    }

    /**
     * Calcula la distancia máxima permitida según el tamaño de la búsqueda.
     *
     * @param queryLength longitud del texto buscado
     * @return distancia máxima permitida
     */
    private int calculateMaxDistance(int queryLength) {

        if (queryLength <= 3) {
            return 0;
        }

        if (queryLength <= 6) {
            return 1;
        }

        return 2;
    }

    /**
     * Calcula la distancia de Levenshtein entre dos textos.
     *
     * @param first primer texto
     * @param second segundo texto
     * @return distancia entre los textos
     */
    private int levenshteinDistance(String first, String second) {

        int[] previous = new int[second.length() + 1];
        int[] current = new int[second.length() + 1];

        for (int j = 0; j <= second.length(); j++) {
            previous[j] = j;
        }

        for (int i = 1; i <= first.length(); i++) {

            current[0] = i;

            for (int j = 1; j <= second.length(); j++) {

                int insertion = current[j - 1] + 1;
                int deletion = previous[j] + 1;

                int substitution =
                        previous[j - 1]
                                + (first.charAt(i - 1) == second.charAt(j - 1)
                                ? 0
                                : 1);

                current[j] = Math.min(
                        Math.min(insertion, deletion),
                        substitution
                );
            }

            int[] temporary = previous;
            previous = current;
            current = temporary;
        }

        return previous[second.length()];
    }
}