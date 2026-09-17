package com.liverpool.orders.application.port.in;

import com.liverpool.orders.domain.model.Customer;

import java.util.Optional;

/**
 * Casos de uso para gestionar clientes.
 */
public interface CustomerUseCase {

    /**
     * Crea un cliente.
     *
     * @param customer cliente a crear
     * @return cliente creado
     */
    Customer create(Customer customer);

    /**
     * Busca un cliente por su userId.
     *
     * @param userId identificador del cliente
     * @return cliente encontrado
     */
    Optional<Customer> findByUserId(String userId);

    /**
     * Actualiza un cliente.
     *
     * @param userId identificador del cliente
     * @param customer datos actualizados
     * @return cliente actualizado
     */
    Customer update(String userId, Customer customer);

    /**
     * Elimina un cliente.
     *
     * @param userId identificador del cliente
     */
    void delete(String userId);
}