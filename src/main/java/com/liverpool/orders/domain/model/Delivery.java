package com.liverpool.orders.domain.model;

import jakarta.validation.constraints.NotBlank;

public class Delivery {

    private String deliveryId;

    @NotBlank
    private String userId;

    @NotBlank
    private String shippingAddress;

    public Delivery() {
    }

    public Delivery(
            String deliveryId,
            String userId,
            String shippingAddress) {

        this.deliveryId = deliveryId;
        this.userId = userId;
        this.shippingAddress = shippingAddress;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}