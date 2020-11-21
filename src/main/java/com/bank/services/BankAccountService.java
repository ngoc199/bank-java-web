package com.bank.services;

import java.util.List;
import java.util.UUID;

import com.bank.entities.BankAccount;
import com.bank.entities.Customer;
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

    /**
     * Find bank account by id
     * 
     * @param id
     * @return bankAccount
     */
    public BankAccount findBankAccountById(UUID id) {
        return bankAccountRepository.findById(id).get();
    }

    /**
     * Find customer's bank accounts
     * 
     * @param customer
     * @return listBankAccounts
     */
    public List<BankAccount> findBankAccountsByCustomer(Customer customer) {
        return bankAccountRepository.findByCustomer(customer);
    }

    /**
     * Save bank account to the database
     * 
     * @param bankAccount
     * @return bankAccount
     */
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Delete bank account by id
     * 
     * @param id
     */
    public void deleteBankAccountById(UUID id) {
        bankAccountRepository.deleteById(id);
    }
}
