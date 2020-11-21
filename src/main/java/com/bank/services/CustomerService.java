package com.bank.services;

import java.util.List;

import com.bank.entities.Customer;
import com.bank.repositories.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Find all customers in the databases
     * 
     * @return listCustomers
     */
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
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

    /**
     * Save customer to the database
     * 
     * @param customer
     * @return customer
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Delete customer by id
     * 
     * @param id
     */
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }

}
