package com.bank.repositories;

import java.util.List;
import java.util.UUID;

import com.bank.entities.BankAccount;
import com.bank.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    public List<BankAccount> findByCustomer(Customer customer);
}
