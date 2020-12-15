package com.bank.repositories;

import java.util.List;

import com.bank.entities.Customer;
import com.bank.entities.Exchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    public List<Exchange> findByCustomer(Customer customer);
}
