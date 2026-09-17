package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.model.OrderItem;
import com.liverpool.orders.application.port.in.OrderUseCase;
import com.liverpool.orders.application.port.out.ExternalItemPort;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.application.port.out.OrderRepository;
import com.liverpool.orders.domain.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Servicio para realizar operaciones relacionadas con pedidos.
 */
@Service
public class OrderService implements OrderUseCase {

    private final ExternalOrderPort externalOrderPort;
    private final ExternalItemPort externalItemPort;
    private final OrderRepository orderRepository;

    /**
     * Constructor del servicio de pedidos.
     *
     * @param externalOrderPort acceso a los pedidos externos
     * @param externalItemPort acceso a los artículos externos
     * @param orderRepository repositorio de pedidos
     */
    public OrderService(
            ExternalOrderPort externalOrderPort,
            ExternalItemPort externalItemPort,
            OrderRepository orderRepository) {

        this.externalOrderPort = externalOrderPort;
        this.externalItemPort = externalItemPort;
        this.orderRepository = orderRepository;
    }

    /**
     * Obtiene los pedidos y sus artículos asociados.
     *
     * @return lista de pedidos
     */
    public List<Order> getOrders() {

        List<Order> orders = externalOrderPort.getOrders();
        List<OrderItem> items = externalItemPort.getItems();

        Map<String, OrderItem> itemsById = items.stream()
                .collect(Collectors.toMap(
                        OrderItem::getItemId,
                        Function.identity()
                ));

        orders.forEach(order -> {

            List<OrderItem> orderItems = order.getItemIds()
                    .stream()
                    .map(itemsById::get)
                    .filter(item -> item != null)
                    .collect(Collectors.toList());

            order.setItems(orderItems);
        });

        return orders;
    }

    /**
     * Crea un pedido.
     *
     * @param order pedido a crear
     * @return pedido creado
     */
    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return pedido encontrado
     */
    @Override
    public Optional<Order> findByOrderRef(String orderRef) {
        return orderRepository.findByOrderRef(orderRef);
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param orderRef referencia del pedido
     * @param order nuevos datos del pedido
     * @return pedido actualizado
     * @throws OrderNotFoundException si el pedido no existe
     */
    @Override
    public Order update(String orderRef, Order order) {

        Order existingOrder = orderRepository
                .findByOrderRef(orderRef)
                .orElseThrow(() -> new OrderNotFoundException(orderRef));

        existingOrder.setUserId(order.getUserId());
        existingOrder.setCanal(order.getCanal());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setStoreName(order.getStoreName());
        existingOrder.setItems(order.getItems());
        existingOrder.setItemIds(order.getItemIds());

        return orderRepository.save(existingOrder);
    }

    /**
     * Elimina un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     */
    @Override
    public void delete(String orderRef) {
        orderRepository.deleteByOrderRef(orderRef);
    }
}