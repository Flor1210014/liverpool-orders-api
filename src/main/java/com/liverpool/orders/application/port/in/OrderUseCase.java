package com.liverpool.orders.application.port.in;

import com.liverpool.orders.application.model.Order;

import java.util.Optional;

/**
 * Casos de uso para gestionar pedidos.
 */
public interface OrderUseCase {

    /**
     * Crea un pedido.
     *
     * @param order pedido a crear
     * @return pedido creado
     */
    Order create(Order order);

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return pedido encontrado
     */
    Optional<Order> findByOrderRef(String orderRef);

    /**
     * Actualiza un pedido.
     *
     * @param orderRef referencia del pedido
     * @param order datos actualizados
     * @return pedido actualizado
     */
    Order update(String orderRef, Order order);

    /**
     * Elimina un pedido.
     *
     * @param orderRef referencia del pedido
     */
    void delete(String orderRef);
}