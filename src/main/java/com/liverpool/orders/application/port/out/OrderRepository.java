package com.liverpool.orders.application.port.out;

import com.liverpool.orders.application.model.Order;

import java.util.Optional;

/**
 * Puerto para acceder a los pedidos.
 */
public interface OrderRepository {

    /**
     * Guarda un pedido.
     *
     * @param order pedido a guardar
     * @return pedido guardado
     */
    Order save(Order order);

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return pedido encontrado
     */
    Optional<Order> findByOrderRef(String orderRef);

    /**
     * Elimina un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     */
    void deleteByOrderRef(String orderRef);
}