package com.liverpool.orders.infrastructure.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio de MongoDB para las entregas.
 */
public interface DeliveryMongoRepository
        extends MongoRepository<DeliveryDocument, String> {

    /**
     * Busca una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     * @return documento encontrado
     */
    Optional<DeliveryDocument> findByDeliveryId(String deliveryId);

    /**
     * Elimina una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     */
    void deleteByDeliveryId(String deliveryId);
}