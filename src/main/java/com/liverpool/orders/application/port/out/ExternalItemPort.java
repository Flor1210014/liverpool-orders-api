package com.liverpool.orders.application.port.out;

import com.liverpool.orders.application.model.OrderItem;

import java.util.List;

/**
 * Puerto para obtener los productos de la API externa.
 */
public interface ExternalItemPort {

    /**
     * Obtiene los productos.
     *
     * @return lista de productos
     */
    List<OrderItem> getItems();
}