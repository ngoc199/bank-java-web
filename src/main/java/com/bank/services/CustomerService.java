package com.bank.services;

import java.util.List;

import javax.transaction.Transactional;

import com.bank.entities.Customer;
import com.bank.repositories.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> findCustomersByName(String name) {
        return customerRepository.findByNameContains(name);
    }

    /**
     * Find customer by id
     * 
     * @param id
     * @return customer
     */
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).get();
    }

    @Transactional(rollbackOn = Exception.class)
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
