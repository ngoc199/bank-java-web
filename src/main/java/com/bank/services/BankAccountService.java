package com.bank.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.bank.entities.BankAccount;
import com.bank.repositories.BankAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Find all bank accounts in the database
     * 
     * @return listBankAccounts
     */
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountById(UUID id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    /**
     * Delete bank account by id
     * 
     * @param id
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteBankAccountById(UUID id) {
        bankAccountRepository.deleteById(id);
    }
}
