package com.liverpool.orders.infrastructure.adapter.out.mockapi.model;

import java.util.List;

/**
 * Modelo que representa un pedido recibido desde MockAPI.
 */
public class ExternalOrder {

    private String orderRef;
    private String userId;
    private String canal;
    private String orderStatus;
    private Boolean marketPlace;
    private Boolean giftRegistry;
    private List<String> items;
    private String storeName;
    private String id;

    public ExternalOrder() {
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

    public Boolean getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(Boolean marketPlace) {
        this.marketPlace = marketPlace;
    }

    public Boolean getGiftRegistry() {
        return giftRegistry;
    }

    public void setGiftRegistry(Boolean giftRegistry) {
        this.giftRegistry = giftRegistry;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
