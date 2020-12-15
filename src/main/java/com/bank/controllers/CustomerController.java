package com.bank.controllers;

import java.util.List;

import javax.validation.Valid;

import com.bank.entities.Customer;
import com.bank.services.CustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.findAllCustomers();
    }

    @PostMapping
    public Customer createNewCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") long id) {
        return customerService.findCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomerById(@PathVariable("id") long id, @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.findCustomerById(id);
        if (customer != null) {
            updatedCustomer.setId(customer.getId());
            return customerService.saveCustomer(updatedCustomer);
        }
        return null;
    }
}
