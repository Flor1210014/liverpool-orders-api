package com.liverpool.orders.application.port.in;

import com.liverpool.orders.domain.model.Delivery;

import java.util.Optional;

/**
 * Casos de uso para gestionar entregas.
 */
public interface DeliveryUseCase {

    /**
     * Crea una entrega.
     *
     * @param delivery entrega a crear
     * @return entrega creada
     */
    Delivery create(Delivery delivery);

    /**
     * Busca una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     * @return entrega encontrada
     */
    Optional<Delivery> findByDeliveryId(String deliveryId);

    /**
     * Actualiza una entrega.
     *
     * @param deliveryId identificador de la entrega
     * @param delivery datos actualizados
     * @return entrega actualizada
     */
    Delivery update(String deliveryId, Delivery delivery);

    /**
     * Elimina una entrega.
     *
     * @param deliveryId identificador de la entrega
     */
    void delete(String deliveryId);
}