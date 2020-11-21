package com.bank.repositories;

import java.util.UUID;

import com.bank.entities.SavingAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, UUID> {

}
