package com.bank.controllers;

import java.util.UUID;

import javax.validation.Valid;

import com.bank.entities.SavingAccount;
import com.bank.services.SavingAccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/saving-accounts")
@RestController
public class SavingAccountController {

    private final SavingAccountService savingAccountService;

    public SavingAccountController(SavingAccountService savingAccountService) {
        this.savingAccountService = savingAccountService;
    }

    @GetMapping("/{id}")
    public SavingAccount getSavingAccountById(@PathVariable("id") UUID id) {
        return savingAccountService.findSavingAccountById(id);
    }

    @PutMapping("/{id}")
    public SavingAccount updateSavingAccount(@PathVariable("id") UUID id,
            @Valid @RequestBody SavingAccount updatedSavingAccount) {
        SavingAccount savingAccount = savingAccountService.findSavingAccountById(id);
        if (savingAccount != null) {
            updatedSavingAccount.setAccountId(savingAccount.getAccountId());
            return savingAccountService.saveSavingAccount(savingAccount);
        }
        return null;
    }
}
