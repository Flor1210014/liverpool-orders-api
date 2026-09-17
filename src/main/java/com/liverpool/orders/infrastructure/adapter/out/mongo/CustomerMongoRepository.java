package com.liverpool.orders.infrastructure.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio de MongoDB para los clientes.
 */
public interface CustomerMongoRepository
        extends MongoRepository<CustomerDocument, String> {

    /**
     * Busca un cliente por su identificador de usuario.
     *
     * @param userId identificador del usuario
     * @return documento del cliente encontrado
     */
    Optional<CustomerDocument> findByUserId(String userId);

    /**
     * Elimina un cliente por su identificador de usuario.
     *
     * @param userId identificador del usuario
     */
    void deleteByUserId(String userId);
}