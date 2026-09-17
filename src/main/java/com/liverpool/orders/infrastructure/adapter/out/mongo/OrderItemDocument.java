package com.liverpool.orders.infrastructure.adapter.out.mongo;

/**
 * Documento de item de pedido almacenado en MongoDB.
 */
public class OrderItemDocument {

    private String itemId;
    private Integer quantity;

    public OrderItemDocument() {
    }

    public OrderItemDocument(String itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}