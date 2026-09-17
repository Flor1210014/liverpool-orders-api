package com.liverpool.orders.infrastructure.adapter.out.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento de entrega almacenado en MongoDB.
 */
@Document(collection = "deliveries")
public class DeliveryDocument {

    @Id
    private String id;

    private String deliveryId;
    private String userId;
    private String shippingAddress;

    public DeliveryDocument() {
    }

    public DeliveryDocument(
            String id,
            String deliveryId,
            String userId,
            String shippingAddress) {

        this.id = id;
        this.deliveryId = deliveryId;
        this.userId = userId;
        this.shippingAddress = shippingAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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