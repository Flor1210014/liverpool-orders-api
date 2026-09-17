package com.liverpool.orders.application.service;

import com.liverpool.orders.application.port.in.DeliveryUseCase;
import com.liverpool.orders.application.port.out.DeliveryRepository;
import com.liverpool.orders.domain.exception.DeliveryNotFoundException;
import com.liverpool.orders.domain.model.Delivery;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para realizar operaciones relacionadas con entregas.
 */
@Service
public class DeliveryService implements DeliveryUseCase {

    private final DeliveryRepository deliveryRepository;

    /**
     * Constructor del servicio de entregas.
     *
     * @param deliveryRepository repositorio de entregas
     */
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    /**
     * Crea una entrega.
     *
     * @param delivery entrega a crear
     * @return entrega creada
     */
    @Override
    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    /**
     * Busca una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     * @return entrega encontrada
     */
    @Override
    public Optional<Delivery> findByDeliveryId(String deliveryId) {
        return deliveryRepository.findByDeliveryId(deliveryId);
    }

    /**
     * Actualiza una entrega existente.
     *
     * @param deliveryId identificador de la entrega
     * @param delivery nuevos datos de la entrega
     * @return entrega actualizada
     * @throws DeliveryNotFoundException si la entrega no existe
     */
    @Override
    public Delivery update(String deliveryId, Delivery delivery) {

        Delivery existingDelivery = deliveryRepository
                .findByDeliveryId(deliveryId)
                .orElseThrow(() -> new DeliveryNotFoundException(deliveryId));

        existingDelivery.setUserId(delivery.getUserId());
        existingDelivery.setShippingAddress(delivery.getShippingAddress());

        return deliveryRepository.save(existingDelivery);
    }

    /**
     * Elimina una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     */
    @Override
    public void delete(String deliveryId) {
        deliveryRepository.deleteByDeliveryId(deliveryId);
    }
}