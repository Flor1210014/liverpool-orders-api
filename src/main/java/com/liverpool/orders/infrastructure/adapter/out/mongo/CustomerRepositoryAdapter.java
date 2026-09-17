package com.liverpool.orders.infrastructure.adapter.out.mongo;

import com.liverpool.orders.application.port.out.CustomerRepository;
import com.liverpool.orders.domain.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementación del repositorio de clientes usando MongoDB.
 */
@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerMongoRepository customerMongoRepository;

    public CustomerRepositoryAdapter(
            CustomerMongoRepository customerMongoRepository) {
        this.customerMongoRepository = customerMongoRepository;
    }
    @Override
    public Customer save(Customer customer) {

        Optional<CustomerDocument> existingDocument =
                customerMongoRepository.findByUserId(customer.getUserId());

        String id = existingDocument
                .map(CustomerDocument::getId)
                .orElse(null);

        CustomerDocument document = toDocument(customer);
        document.setId(id);

        return toDomain(customerMongoRepository.save(document));
    }

    @Override
    public Optional<Customer> findByUserId(String userId) {

        return customerMongoRepository
                .findByUserId(userId)
                .map(this::toDomain);
    }

    @Override
    public void deleteByUserId(String userId) {

        customerMongoRepository.deleteByUserId(userId);
    }

    private CustomerDocument toDocument(Customer customer) {

        CustomerDocument document = new CustomerDocument();

        document.setUserId(customer.getUserId());
        document.setFirstName(customer.getFirstName());
        document.setLastName(customer.getLastName());
        document.setSecondLastName(customer.getSecondLastName());
        document.setEmail(customer.getEmail());
        document.setOrders(customer.getOrders());

        return document;
    }

    private Customer toDomain(CustomerDocument document) {

        return new Customer(
                document.getUserId(),
                document.getFirstName(),
                document.getLastName(),
                document.getSecondLastName(),
                document.getEmail(),
                document.getOrders()
        );
    }

}
