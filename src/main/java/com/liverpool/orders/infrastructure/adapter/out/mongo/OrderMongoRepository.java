package com.liverpool.orders.infrastructure.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio de MongoDB para los pedidos.
 */
public interface OrderMongoRepository
        extends MongoRepository<OrderDocument, String> {

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return documento del pedido encontrado
     */
    Optional<OrderDocument> findByOrderRef(String orderRef);

    /**
     * Elimina un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     */
    void deleteByOrderRef(String orderRef);
}