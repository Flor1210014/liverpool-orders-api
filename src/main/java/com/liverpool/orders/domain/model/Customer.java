package com.liverpool.orders.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class Customer {
    @NotBlank
    private String userId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String secondLastName;
    @NotBlank
    @Email
    private String email;
    private List<String> orders;

    public Customer() {
    }

    public Customer(
            String userId,
            String firstName,
            String lastName,
            String secondLastName,
            String email,
            List<String> orders) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }
}
