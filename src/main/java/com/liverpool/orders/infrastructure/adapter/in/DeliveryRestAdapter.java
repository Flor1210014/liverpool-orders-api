package com.liverpool.orders.infrastructure.adapter.in;

import com.liverpool.orders.application.port.in.DeliveryUseCase;
import com.liverpool.orders.domain.exception.DeliveryNotFoundException;
import com.liverpool.orders.domain.model.Delivery;
import jakarta.validation.Valid;
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
 * Adaptador REST para la gestión de entregas.
 */
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryRestAdapter {

    private final DeliveryUseCase deliveryUseCase;

    public DeliveryRestAdapter(DeliveryUseCase deliveryUseCase) {
        this.deliveryUseCase = deliveryUseCase;
    }

    /**
     * Crea una nueva entrega.
     *
     * @param delivery datos de la entrega
     * @return entrega creada
     */
    @PostMapping
    public ResponseEntity<Delivery> create(
            @RequestBody @Valid Delivery delivery) {

        Delivery created = deliveryUseCase.create(delivery);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    /**
     * Busca una entrega por su identificador.
     *
     * @param deliveryId identificador de la entrega
     * @return entrega encontrada
     */
    @GetMapping("/{deliveryId}")
    public ResponseEntity<Delivery> findByDeliveryId(
            @PathVariable String deliveryId) {

        Delivery delivery = deliveryUseCase.findByDeliveryId(deliveryId)
                .orElseThrow(
                        () -> new DeliveryNotFoundException(deliveryId));

        return ResponseEntity.ok(delivery);
    }

    /**
     * Actualiza una entrega existente.
     *
     * @param deliveryId identificador de la entrega
     * @param delivery datos actualizados
     * @return entrega actualizada
     */
    @PutMapping("/{deliveryId}")
    public ResponseEntity<Delivery> update(
            @PathVariable String deliveryId,
            @RequestBody @Valid Delivery delivery) {

        return ResponseEntity.ok(
                deliveryUseCase.update(deliveryId, delivery)
        );
    }

    /**
     * Elimina una entrega.
     *
     * @param deliveryId identificador de la entrega
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<Void> delete(
            @PathVariable String deliveryId) {

        deliveryUseCase.delete(deliveryId);

        return ResponseEntity.noContent().build();
    }
}