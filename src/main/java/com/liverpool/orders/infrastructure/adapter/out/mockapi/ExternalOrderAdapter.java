package com.liverpool.orders.infrastructure.adapter.out.mockapi;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.infrastructure.adapter.out.mockapi.model.ExternalOrder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador para consultar pedidos desde MockAPI.
 */
@Component
public class ExternalOrderAdapter implements ExternalOrderPort {
    private final RestClient restClient;

    public ExternalOrderAdapter(RestClient mockApiRestClient) {
        this.restClient = mockApiRestClient;
    }

    /**
     * Obtiene los pedidos desde el servicio externo.
     *
     * @return lista de pedidos
     */
    @Override
    public List<Order> getOrders() {

        ExternalOrder[] externalOrders = restClient
                .get()
                .uri("/pedidos")
                .retrieve()
                .body(ExternalOrder[].class);

        if (externalOrders == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(externalOrders)
                .map(this::toApplicationModel)
                .collect(Collectors.toList());
    }

    private Order toApplicationModel(ExternalOrder externalOrder) {

        return new Order(
                externalOrder.getOrderRef(),
                externalOrder.getUserId(),
                externalOrder.getCanal(),
                externalOrder.getOrderStatus(),
                externalOrder.getStoreName(),
                externalOrder.getItems(),
                Collections.emptyList()
        );
    }
}
