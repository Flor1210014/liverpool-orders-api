package com.liverpool.orders.infrastructure.adapter.out.mockapi;

import com.liverpool.orders.application.model.OrderItem;
import com.liverpool.orders.application.port.out.ExternalItemPort;
import com.liverpool.orders.infrastructure.adapter.out.mockapi.model.ExternalItem;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador para consultar items desde MockAPI.
 */
@Component
public class ExternalItemAdapter implements ExternalItemPort {

    private final RestClient restClient;

    public ExternalItemAdapter(RestClient mockApiRestClient) {
        this.restClient = mockApiRestClient;
    }

    /**
     * Obtiene los items desde el servicio externo.
     *
     * @return lista de items
     */
    @Override
    public List<OrderItem> getItems() {

        ExternalItem[] externalItems = restClient
                .get()
                .uri("/items")
                .retrieve()
                .body(ExternalItem[].class);

        if (externalItems == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(externalItems)
                .map(this::toApplicationModel)
                .collect(Collectors.toList());
    }

    private OrderItem toApplicationModel(ExternalItem externalItem) {

        return new OrderItem(
                externalItem.getItemId(),
                externalItem.getQuantity(),
                externalItem.getDisplayName()
        );
    }
}
