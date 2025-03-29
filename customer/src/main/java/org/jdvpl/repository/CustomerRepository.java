package org.jdvpl.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.jdvpl.entities.Customer;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        entityManager.flush();
        System.out.println(customer);
        return  customer;
    }

    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
    @Transactional
    public void delete(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
    @Transactional
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }
    @Transactional
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }
}
