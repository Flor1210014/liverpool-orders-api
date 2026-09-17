package com.liverpool.orders.application.service;

import com.liverpool.orders.application.model.Order;
import com.liverpool.orders.application.port.in.CustomerUseCase;
import com.liverpool.orders.application.port.out.CustomerRepository;
import com.liverpool.orders.application.port.out.ExternalOrderPort;
import com.liverpool.orders.domain.exception.CustomerNotFoundException;
import com.liverpool.orders.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para realizar operaciones relacionadas con clientes.
 */
@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerRepository customerRepository;
    private final ExternalOrderPort externalOrderPort;

    /**
     * Constructor del servicio de clientes.
     *
     * @param customerRepository repositorio de clientes
     * @param externalOrderPort acceso a los pedidos externos
     */
    public CustomerService(
            CustomerRepository customerRepository,
            ExternalOrderPort externalOrderPort) {

        this.customerRepository = customerRepository;
        this.externalOrderPort = externalOrderPort;
    }

    /**
     * Crea un cliente y obtiene sus pedidos asociados.
     *
     * @param customer cliente a crear
     * @return cliente creado
     */
    @Override
    public Customer create(Customer customer) {

        List<String> orders = findOrderRefsByUserId(customer.getUserId());

        customer.setOrders(orders);

        return customerRepository.save(customer);
    }

    /**
     * Busca un cliente por su identificador.
     *
     * @param userId identificador del cliente
     * @return cliente encontrado
     */
    @Override
    public Optional<Customer> findByUserId(String userId) {
        return customerRepository.findByUserId(userId);
    }

    /**
     * Actualiza los datos de un cliente.
     *
     * @param userId identificador del cliente
     * @param customer nuevos datos del cliente
     * @return cliente actualizado
     * @throws CustomerNotFoundException si el cliente no existe
     */
    @Override
    public Customer update(String userId, Customer customer) {
        Customer existingCustomer = customerRepository
                .findByUserId(userId)
                .orElseThrow(() -> new CustomerNotFoundException(userId));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setSecondLastName(customer.getSecondLastName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    /**
     * Elimina un cliente por su identificador.
     *
     * @param userId identificador del cliente
     */
    @Override
    public void delete(String userId) {
        customerRepository.deleteByUserId(userId);
    }

    /**
     * Obtiene las referencias de pedidos asociadas a un cliente.
     *
     * @param userId identificador del cliente
     * @return lista de referencias de pedidos
     */
    private List<String> findOrderRefsByUserId(String userId) {

        return externalOrderPort.getOrders()
                .stream()
                .filter(order -> userId.equals(order.getUserId()))
                .map(Order::getOrderRef)
                .collect(Collectors.toList());
    }
}