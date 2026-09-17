package com.liverpool.orders.infrastructure.adapter.out.mongo;

import com.liverpool.orders.application.port.out.DeliveryRepository;
import com.liverpool.orders.domain.model.Delivery;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryRepositoryAdapter implements DeliveryRepository {

    private final DeliveryMongoRepository deliveryMongoRepository;

    public DeliveryRepositoryAdapter(
            DeliveryMongoRepository deliveryMongoRepository) {
        this.deliveryMongoRepository = deliveryMongoRepository;
    }

    @Override
    public Delivery save(Delivery delivery) {

        Optional<DeliveryDocument> existingDocument =
                deliveryMongoRepository.findByDeliveryId(delivery.getDeliveryId());

        String id = existingDocument
                .map(DeliveryDocument::getId)
                .orElse(null);

        DeliveryDocument document = new DeliveryDocument(
                id,
                delivery.getDeliveryId(),
                delivery.getUserId(),
                delivery.getShippingAddress()
        );

        DeliveryDocument savedDocument =
                deliveryMongoRepository.save(document);

        return toDomain(savedDocument);
    }

    @Override
    public Optional<Delivery> findByDeliveryId(String deliveryId) {
        return deliveryMongoRepository
                .findByDeliveryId(deliveryId)
                .map(this::toDomain);
    }

    @Override
    public void deleteByDeliveryId(String deliveryId) {
        deliveryMongoRepository.deleteByDeliveryId(deliveryId);
    }

    private Delivery toDomain(DeliveryDocument document) {
        return new Delivery(
                document.getDeliveryId(),
                document.getUserId(),
                document.getShippingAddress()
        );
    }
}