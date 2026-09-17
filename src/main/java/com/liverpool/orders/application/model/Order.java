package com.liverpool.orders.application.model;

import java.util.List;

public class Order {
    private String orderRef;
    private String userId;
    private String canal;
    private String orderStatus;
    private String storeName;
    private List<OrderItem> items;
    private List<String> itemIds;

    public Order() {
    }

    public Order(
            String orderRef,
            String userId,
            String canal,
            String orderStatus,
            String storeName,
            List<String> itemIds,
            List<OrderItem> items) {

        this.orderRef = orderRef;
        this.userId = userId;
        this.canal = canal;
        this.orderStatus = orderStatus;
        this.storeName = storeName;
        this.itemIds = itemIds;
        this.items = items;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }
}
