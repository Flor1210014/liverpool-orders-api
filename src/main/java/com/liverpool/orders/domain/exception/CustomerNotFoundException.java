package com.liverpool.orders.domain.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String userId) {
        super("Customer not found with userId: " + userId);
    }
}
