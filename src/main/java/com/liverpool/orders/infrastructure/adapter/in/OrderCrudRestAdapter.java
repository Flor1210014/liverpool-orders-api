package com.liverpool.orders.infrastructure.adapter.in;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.port.in.OrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Adaptador REST para las operaciones CRUD de pedidos.
 */
@RestController
@RequestMapping("/api/v1/order-crud")
public class OrderCrudRestAdapter {

    private final OrderUseCase orderUseCase;

    public OrderCrudRestAdapter(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    /**
     * Crea un nuevo pedido.
     *
     * @param order datos del pedido
     * @return pedido creado
     */
    @PostMapping
    public ResponseEntity<Order> create(
            @RequestBody Order order) {

        Order createdOrder = orderUseCase.create(order);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdOrder);
    }

    /**
     * Busca un pedido por su referencia.
     *
     * @param orderRef referencia del pedido
     * @return pedido encontrado o estado 404
     */
    @GetMapping("/{orderRef}")
    public ResponseEntity<Order> findByOrderRef(
            @PathVariable String orderRef) {

        return orderUseCase.findByOrderRef(orderRef)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param orderRef referencia del pedido
     * @param order datos actualizados
     * @return pedido actualizado
     */
    @PutMapping("/{orderRef}")
    public ResponseEntity<Order> update(
            @PathVariable String orderRef,
            @RequestBody Order order) {

        return ResponseEntity.ok(
                orderUseCase.update(orderRef, order)
        );
    }

    /**
     * Elimina un pedido.
     *
     * @param orderRef referencia del pedido
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{orderRef}")
    public ResponseEntity<Void> delete(
            @PathVariable String orderRef) {

        orderUseCase.delete(orderRef);

        return ResponseEntity.noContent().build();
    }
}