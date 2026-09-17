package com.liverpool.orders.infrastructure.adapter.out.mongo;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.model.OrderItem;
import com.liverpool.orders.application.port.out.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de pedidos usando MongoDB.
 */
@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderMongoRepository orderMongoRepository;

    public OrderRepositoryAdapter(OrderMongoRepository orderMongoRepository) {
        this.orderMongoRepository = orderMongoRepository;
    }

    /**
     * Guarda o actualiza un pedido.
     *
     * @param order pedido a guardar
     * @return pedido guardado
     */
    @Override
    public Order save(Order order) {

        List<OrderItemDocument> items = order.getItems()
                .stream()
                .map(item -> new OrderItemDocument(
                        item.getItemId(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        Optional<OrderDocument> existingDocument =
                orderMongoRepository.findByOrderRef(order.getOrderRef());

        String id = existingDocument
                .map(OrderDocument::getId)
                .orElse(null);

        OrderDocument document = new OrderDocument(
                id,
                order.getOrderRef(),
                order.getUserId(),
                order.getCanal(),
                order.getOrderStatus(),
                order.getStoreName(),
                items
        );

        OrderDocument savedDocument = orderMongoRepository.save(document);

        return toDomain(savedDocument);
    }

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return pedido encontrado
     */
    @Override
    public Optional<Order> findByOrderRef(String orderRef) {
        return orderMongoRepository
                .findByOrderRef(orderRef)
                .map(this::toDomain);
    }

    /**
     * Elimina un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     */
    @Override
    public void deleteByOrderRef(String orderRef) {
        orderMongoRepository.deleteByOrderRef(orderRef);
    }

    /**
     * Convierte un documento de MongoDB al modelo de aplicación.
     *
     * @param document documento de MongoDB
     * @return pedido convertido
     */
    private Order toDomain(OrderDocument document) {

        List<OrderItem> items = document.getItems()
                .stream()
                .map(item -> new OrderItem(
                        item.getItemId(),
                        item.getQuantity(),
                        null
                ))
                .collect(Collectors.toList());

        return new Order(
                document.getOrderRef(),
                document.getUserId(),
                document.getCanal(),
                document.getOrderStatus(),
                document.getStoreName(),
                items.stream()
                        .map(OrderItem::getItemId)
                        .collect(Collectors.toList()),
                items
        );
    }
}