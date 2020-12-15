package com.bank.repositories;

import java.util.UUID;

import com.bank.entities.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
