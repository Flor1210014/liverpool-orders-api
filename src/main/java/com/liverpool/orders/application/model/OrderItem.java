package com.liverpool.orders.application.model;

public class OrderItem {
    private String itemId;
    private Integer quantity;
    private String displayName;

    public OrderItem() {
    }

    public OrderItem(String itemId, Integer quantity, String displayName) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
