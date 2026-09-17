package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.port.out.CustomerRepository;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private ExternalOrderPort externalOrderPort;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        externalOrderPort = mock(ExternalOrderPort.class);

        customerService = new CustomerService(
                customerRepository,
                externalOrderPort
        );
    }

    @Test
    void shouldCreateCustomerWithOrders() {
        Customer customer = new Customer(
                "USER001",
                "Flor",
                "Arriaga",
                "Espinosa",
                "flor@test.com",
                null
        );

        Order order = new Order();
        order.setOrderRef("ORD001");
        order.setUserId("USER001");

        when(externalOrderPort.getOrders())
                .thenReturn(Collections.singletonList(order));

        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.create(customer);

        assertEquals("USER001", result.getUserId());
        assertEquals(
                Collections.singletonList("ORD001"),
                result.getOrders()
        );

        verify(customerRepository).save(customer);
    }

    @Test
    void shouldFindCustomerByUserId() {
        Customer customer = new Customer(
                "USER001",
                "Flor",
                "Arriaga",
                "Espinosa",
                "flor@test.com",
                Collections.emptyList()
        );

        when(customerRepository.findByUserId("USER001"))
                .thenReturn(Optional.of(customer));

        Optional<Customer> result =
                customerService.findByUserId("USER001");

        assertTrue(result.isPresent());
        assertEquals("USER001", result.get().getUserId());
    }
}