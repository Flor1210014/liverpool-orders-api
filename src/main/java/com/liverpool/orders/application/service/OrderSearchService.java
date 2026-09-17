package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.model.OrderItem;
import com.liverpool.orders.application.service.search.FuzzyTextMatcher;
import com.liverpool.orders.application.service.search.TextNormalizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para realizar búsquedas de pedidos.
 */
@Service
public class OrderSearchService {

    private final OrderService orderService;
    private final TextNormalizer textNormalizer;
    private final FuzzyTextMatcher fuzzyTextMatcher;

    /**
     * Constructor del servicio de búsqueda.
     *
     * @param orderService servicio de pedidos
     * @param textNormalizer normalizador de texto
     * @param fuzzyTextMatcher comparador de texto
     */
    public OrderSearchService(
            OrderService orderService,
            TextNormalizer textNormalizer,
            FuzzyTextMatcher fuzzyTextMatcher) {

        this.orderService = orderService;
        this.textNormalizer = textNormalizer;
        this.fuzzyTextMatcher = fuzzyTextMatcher;
    }

    /**
     * Busca pedidos de acuerdo con el texto proporcionado.
     *
     * @param query texto utilizado para realizar la búsqueda
     * @return lista de pedidos que coinciden con la búsqueda
     */
    public List<Order> search(String query) {

        if (query == null || query.trim().isEmpty()) {
            return orderService.getOrders();
        }

        String normalizedQuery = textNormalizer.normalize(query);

        return orderService.getOrders()
                .stream()
                .filter(order -> matches(order, normalizedQuery))
                .collect(Collectors.toList());
    }

    /**
     * Verifica si un pedido coincide con la búsqueda.
     *
     * @param order pedido a revisar
     * @param query texto de búsqueda normalizado
     * @return true si el pedido coincide
     */
    private boolean matches(Order order, String query) {

        return contains(order.getOrderRef(), query)
                || contains(order.getUserId(), query)
                || contains(order.getCanal(), query)
                || contains(order.getOrderStatus(), query)
                || contains(order.getStoreName(), query)
                || order.getItems().stream()
                .anyMatch(item -> matchesItem(item, query));
    }

    /**
     * Verifica si un artículo coincide con la búsqueda.
     *
     * @param item artículo a revisar
     * @param query texto de búsqueda normalizado
     * @return true si el artículo coincide
     */
    private boolean matchesItem(OrderItem item, String query) {

        return contains(item.getItemId(), query)
                || contains(item.getDisplayName(), query);
    }

    /**
     * Compara un valor con el texto de búsqueda.
     *
     * @param value valor a comparar
     * @param query texto de búsqueda
     * @return true si existe coincidencia
     */
    private boolean contains(String value, String query) {

        String normalizedValue = textNormalizer.normalize(value);
        return fuzzyTextMatcher.matches(normalizedValue, query);
    }
}