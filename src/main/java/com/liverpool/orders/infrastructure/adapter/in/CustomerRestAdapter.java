package com.liverpool.orders.infrastructure.adapter.in;

import com.liverpool.orders.application.port.in.CustomerUseCase;
import com.liverpool.orders.domain.model.Customer;
import com.liverpool.orders.infrastructure.adapter.dto.CustomerRequest;
import com.liverpool.orders.infrastructure.adapter.dto.CustomerResponse;
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
 * Adaptador REST para la gestión de clientes.
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestAdapter {

    private final CustomerUseCase customerUseCase;
    private final CustomerMapper customerMapper;

    public CustomerRestAdapter(
            CustomerUseCase customerUseCase,
            CustomerMapper customerMapper) {

        this.customerUseCase = customerUseCase;
        this.customerMapper = customerMapper;
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param request datos del cliente
     * @return cliente creado
     */
    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest request) {

        Customer customer = customerMapper.toDomain(request);

        Customer created = customerUseCase.create(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerMapper.toResponse(created));
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param userId identificador del cliente
     * @return cliente encontrado o estado 404
     */
    @GetMapping("/{userId}")
    public ResponseEntity<CustomerResponse> findByUserId(
            @PathVariable String userId) {

        return customerUseCase.findByUserId(userId)
                .map(customerMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param userId identificador del cliente
     * @param request datos actualizados
     * @return cliente actualizado
     */
    @PutMapping("/{userId}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable String userId,
            @Valid @RequestBody CustomerRequest request) {

        Customer customer = customerMapper.toDomain(request);

        Customer updated = customerUseCase.update(userId, customer);

        return ResponseEntity
                .ok(customerMapper.toResponse(updated));
    }

    /**
     * Elimina un cliente.
     *
     * @param userId identificador del cliente
     * @return respuesta sin contenido
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(
            @PathVariable String userId) {

        customerUseCase.delete(userId);

        return ResponseEntity.noContent().build();
    }
}