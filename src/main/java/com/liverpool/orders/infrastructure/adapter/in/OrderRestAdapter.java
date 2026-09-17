package com.liverpool.orders.infrastructure.adapter.in;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.service.OrderSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Adaptador REST para la búsqueda de pedidos.
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestAdapter {

    private final OrderSearchService orderSearchService;

    public OrderRestAdapter(OrderSearchService orderSearchService) {
        this.orderSearchService = orderSearchService;
    }

    /**
     * Busca pedidos utilizando un texto de búsqueda.
     *
     * @param q texto utilizado para realizar la búsqueda
     * @return lista de pedidos encontrados
     */
    @GetMapping
    public List<Order> search(
            @RequestParam(required = false) String q) {

        return orderSearchService.search(q);
    }
}