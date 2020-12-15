package com.bank.repositories;

import java.util.List;

import com.bank.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByNameContains(String name);
}
