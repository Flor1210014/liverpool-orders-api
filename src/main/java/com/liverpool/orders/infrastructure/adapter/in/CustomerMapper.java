package com.liverpool.orders.infrastructure.adapter.in;

import com.liverpool.orders.domain.model.Customer;
import com.liverpool.orders.infrastructure.adapter.dto.CustomerRequest;
import com.liverpool.orders.infrastructure.adapter.dto.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapper {
    public Customer toDomain(CustomerRequest request) {

        return new Customer(
                request.getUserId(),
                request.getFirstName(),
                request.getLastName(),
                request.getSecondLastName(),
                request.getEmail(),
                new ArrayList<>()
        );
    }

    public CustomerResponse toResponse(Customer customer) {

        CustomerResponse response = new CustomerResponse();

        response.setUserId(customer.getUserId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setSecondLastName(customer.getSecondLastName());
        response.setEmail(customer.getEmail());
        response.setOrders(customer.getOrders());

        return response;
    }
}
