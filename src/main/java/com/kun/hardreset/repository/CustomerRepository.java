package com.kun.hardreset.repository;

import com.kun.hardreset.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public List<Customer> getCustomers() {
        return entityManager.createQuery("select c from Customer c").getResultList();
    }

    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    public void create(List<Customer> customers) {
        for (Customer customer : customers) {
            entityManager.persist(customer);
        }
    }

    public void deleteCustomer(Customer customer) {
        entityManager.remove(customer);
    }

    public void clearEntity() {
        entityManager.createQuery("delete from Customer").executeUpdate();
    }
}
