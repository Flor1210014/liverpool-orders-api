package com.liverpool.orders.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderRef) {
        super("Order not found with orderRef: " + orderRef);
    }
}
