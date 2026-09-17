package com.liverpool.orders.application.port.out;

import com.liverpool.orders.domain.model.Delivery;

import java.util.Optional;

/**
 * Puerto para acceder a las entregas.
 */
public interface DeliveryRepository {

    /**
     * Guarda una entrega.
     *
     * @param delivery entrega a guardar
     * @return entrega guardada
     */
    Delivery save(Delivery delivery);

    /**
     * Busca una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     * @return entrega encontrada
     */
    Optional<Delivery> findByDeliveryId(String deliveryId);

    /**
     * Elimina una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     */
    void deleteByDeliveryId(String deliveryId);
}