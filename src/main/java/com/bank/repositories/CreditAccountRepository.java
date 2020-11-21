package com.bank.repositories;

import java.util.UUID;

import com.bank.entities.CreditAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, UUID> {

}
