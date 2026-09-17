package com.liverpool.orders.application.service;

import com.liverpool.orders.application.port.out.DeliveryRepository;
import com.liverpool.orders.domain.model.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeliveryServiceTest {

    private DeliveryRepository deliveryRepository;
    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        deliveryRepository = mock(DeliveryRepository.class);
        deliveryService = new DeliveryService(deliveryRepository);
    }

    @Test
    void shouldCreateDelivery() {
        Delivery delivery = new Delivery(
                "DEL001",
                "USER001",
                "Av. Principal 123"
        );

        when(deliveryRepository.save(delivery))
                .thenReturn(delivery);

        Delivery result = deliveryService.create(delivery);

        assertEquals("DEL001", result.getDeliveryId());
        assertEquals("USER001", result.getUserId());
        assertEquals("Av. Principal 123", result.getShippingAddress());

        verify(deliveryRepository).save(delivery);
    }

    @Test
    void shouldFindDeliveryById() {
        Delivery delivery = new Delivery(
                "DEL001",
                "USER001",
                "Av. Principal 123"
        );

        when(deliveryRepository.findByDeliveryId("DEL001"))
                .thenReturn(Optional.of(delivery));

        Optional<Delivery> result =
                deliveryService.findByDeliveryId("DEL001");

        assertTrue(result.isPresent());
        assertEquals("DEL001", result.get().getDeliveryId());
    }
}