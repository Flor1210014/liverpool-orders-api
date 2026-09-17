package com.liverpool.orders.domain.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String deliveryId) {
        super("Delivery not found with deliveryId: " + deliveryId);
    }
}
