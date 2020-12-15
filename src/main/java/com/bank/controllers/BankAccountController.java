package com.bank.controllers;

import java.util.List;
import java.util.UUID;

import com.bank.entities.BankAccount;
import com.bank.services.BankAccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/bank-accounts")
@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.findAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getBankAccountById(@PathVariable("id") UUID id) {
        return bankAccountService.findBankAccountById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccountById(@PathVariable("id") UUID id) {
        bankAccountService.deleteBankAccountById(id);
    }

}
