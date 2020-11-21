package com.bank.services;

import java.util.List;

import com.bank.entities.SavingAccount;
import com.bank.repositories.SavingAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class SavingAccountService {
    private final SavingAccountRepository savingAccountRepository;

    public SavingAccountService(SavingAccountRepository savingAccountRepository) {
        this.savingAccountRepository = savingAccountRepository;
    }

    /**
     * Find all saving accounts in the database
     * 
     * @return listSavingAccounts
     */
    public List<SavingAccount> findAllSavingAccounts() {
        return savingAccountRepository.findAll();
    }
}
