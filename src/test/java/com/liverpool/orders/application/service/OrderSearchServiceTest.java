package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.model.OrderItem;
import com.liverpool.orders.application.port.out.ExternalItemPort;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.application.port.out.OrderRepository;
import com.liverpool.orders.application.service.search.AccentTextNormalizer;
import com.liverpool.orders.application.service.search.FuzzyTextMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderSearchServiceTest {

    private ExternalOrderPort externalOrderPort;
    private ExternalItemPort externalItemPort;
    private OrderSearchService orderSearchService;

    @BeforeEach
    void setUp() {
        externalOrderPort = mock(ExternalOrderPort.class);
        externalItemPort = mock(ExternalItemPort.class);

        OrderRepository orderRepository = mock(OrderRepository.class);

        OrderService orderService = new OrderService(
                externalOrderPort,
                externalItemPort,
                orderRepository
        );

        orderSearchService = new OrderSearchService(
                orderService,
                new AccentTextNormalizer(),
                new FuzzyTextMatcher()
        );
    }

    @Test
    void shouldFindOrderByDisplayName() {
        Order order = new Order();
        order.setOrderRef("ORD001");
        order.setItemIds(
                Collections.singletonList("ITEM001")
        );

        when(externalOrderPort.getOrders())
                .thenReturn(Collections.singletonList(order));

        when(externalItemPort.getItems())
                .thenReturn(Collections.singletonList(
                        new OrderItem(
                                "ITEM001",
                                1,
                                "Pantalón Levi's"
                        )
                ));

        List<Order> result =
                orderSearchService.search("pantalon");

        assertEquals(1, result.size());
        assertEquals(
                "ORD001",
                result.get(0).getOrderRef()
        );
    }

    @Test
    void shouldFindOrderIgnoringAccents() {
        Order order = new Order();
        order.setOrderRef("ORD002");
        order.setItemIds(
                Collections.singletonList("ITEM002")
        );

        when(externalOrderPort.getOrders())
                .thenReturn(Collections.singletonList(order));

        when(externalItemPort.getItems())
                .thenReturn(Collections.singletonList(
                        new OrderItem(
                                "ITEM002",
                                1,
                                "Pantalón"
                        )
                ));

        List<Order> result =
                orderSearchService.search("pantalon");

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnAllOrdersWhenQueryIsEmpty() {
        Order order1 = new Order();
        order1.setOrderRef("ORD001");
        order1.setItemIds(
                Collections.emptyList()
        );

        Order order2 = new Order();
        order2.setOrderRef("ORD002");
        order2.setItemIds(
                Collections.emptyList()
        );

        when(externalOrderPort.getOrders())
                .thenReturn(Arrays.asList(order1, order2));

        when(externalItemPort.getItems())
                .thenReturn(Collections.emptyList());

        List<Order> result =
                orderSearchService.search("");

        assertEquals(2, result.size());
    }
}