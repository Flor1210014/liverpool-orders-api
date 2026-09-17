package com.liverpool.orders.application.port.out;

import com.liverpool.orders.application.model.Order;

import java.util.List;

/**
 * Puerto para obtener los pedidos de la API externa.
 */
public interface ExternalOrderPort {

    /**
     * Obtiene los pedidos.
     *
     * @return lista de pedidos
     */
    List<Order> getOrders();
}