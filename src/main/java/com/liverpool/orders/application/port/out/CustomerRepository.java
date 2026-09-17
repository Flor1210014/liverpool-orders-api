package com.liverpool.orders.application.port.out;

import com.liverpool.orders.domain.model.Customer;

import java.util.Optional;

/**
 * Puerto para acceder a los clientes.
 */
public interface CustomerRepository {

    /**
     * Guarda un cliente.
     *
     * @param customer cliente a guardar
     * @return cliente guardado
     */
    Customer save(Customer customer);

    /**
     * Busca un cliente por su userId.
     *
     * @param userId identificador del cliente
     * @return cliente encontrado
     */
    Optional<Customer> findByUserId(String userId);

    /**
     * Elimina un cliente por su userId.
     *
     * @param userId identificador del cliente
     */
    void deleteByUserId(String userId);
}