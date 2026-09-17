package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.port.out.ExternalItemPort;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.application.port.out.OrderRepository;
import com.liverpool.orders.domain.exception.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private ExternalOrderPort externalOrderPort;
    private ExternalItemPort externalItemPort;
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        externalOrderPort = mock(ExternalOrderPort.class);
        externalItemPort = mock(ExternalItemPort.class);
        orderRepository = mock(OrderRepository.class);

        orderService = new OrderService(
                externalOrderPort,
                externalItemPort,
                orderRepository
        );
    }

    @Test
    void shouldCreateOrder() {
        Order order = new Order();
        order.setOrderRef("ORD001");
        order.setUserId("USER001");
        order.setCanal("WEB");
        order.setOrderStatus("CREATED");
        order.setStoreName("Liverpool");

        when(orderRepository.save(order))
                .thenReturn(order);

        Order result = orderService.create(order);

        assertEquals("ORD001", result.getOrderRef());
        assertEquals("USER001", result.getUserId());
        assertEquals("WEB", result.getCanal());
        assertEquals("CREATED", result.getOrderStatus());

        verify(orderRepository).save(order);
    }

    @Test
    void shouldFindOrderByOrderRef() {
        Order order = new Order();
        order.setOrderRef("ORD001");

        when(orderRepository.findByOrderRef("ORD001"))
                .thenReturn(Optional.of(order));

        Optional<Order> result =
                orderService.findByOrderRef("ORD001");

        assertEquals(true, result.isPresent());
        assertEquals("ORD001", result.get().getOrderRef());
    }

    @Test
    void shouldUpdateOrder() {
        Order existingOrder = new Order();
        existingOrder.setOrderRef("ORD001");
        existingOrder.setUserId("USER001");

        Order updateOrder = new Order();
        updateOrder.setUserId("USER002");
        updateOrder.setCanal("APP");
        updateOrder.setOrderStatus("SHIPPED");
        updateOrder.setStoreName("Liverpool");

        when(orderRepository.findByOrderRef("ORD001"))
                .thenReturn(Optional.of(existingOrder));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order result =
                orderService.update("ORD001", updateOrder);

        assertEquals("ORD001", result.getOrderRef());
        assertEquals("USER002", result.getUserId());
        assertEquals("APP", result.getCanal());
        assertEquals("SHIPPED", result.getOrderStatus());
        assertEquals("Liverpool", result.getStoreName());

        verify(orderRepository).save(existingOrder);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingOrder() {
        Order updateOrder = new Order();

        when(orderRepository.findByOrderRef("ORD999"))
                .thenReturn(Optional.empty());

        assertThrows(
                OrderNotFoundException.class,
                () -> orderService.update("ORD999", updateOrder)
        );
    }
}