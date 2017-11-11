package com.kun.hardreset.service;

import com.kun.hardreset.model.Customer;
import com.kun.hardreset.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.create(customer);
    }

    @Transactional
    public void save(List<Customer> customers) {
        customerRepository.create(customers);
    }

    @Transactional
    public void delete(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }

    @Transactional
    public void clearEntity() {
        customerRepository.clearEntity();
    }

}
