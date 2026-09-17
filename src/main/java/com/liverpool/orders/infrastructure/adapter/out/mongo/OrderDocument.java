package com.liverpool.orders.infrastructure.adapter.out.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Documento de pedido almacenado en MongoDB.
 */
@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String id;

    private String orderRef;
    private String userId;
    private String canal;
    private String orderStatus;
    private String storeName;
    private List<OrderItemDocument> items;

    public OrderDocument() {
    }

    public OrderDocument(
            String id,
            String orderRef,
            String userId,
            String canal,
            String orderStatus,
            String storeName,
            List<OrderItemDocument> items) {

        this.id = id;
        this.orderRef = orderRef;
        this.userId = userId;
        this.canal = canal;
        this.orderStatus = orderStatus;
        this.storeName = storeName;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<OrderItemDocument> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDocument> items) {
        this.items = items;
    }
}