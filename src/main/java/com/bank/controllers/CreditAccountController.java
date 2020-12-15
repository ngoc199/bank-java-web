package com.bank.controllers;

import java.util.UUID;

import javax.validation.Valid;

import com.bank.entities.CreditAccount;
import com.bank.services.CreditAccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/credit-accounts")
@RestController
public class CreditAccountController {
    private final CreditAccountService creditAccountService;

    public CreditAccountController(CreditAccountService creditAccountService) {
        this.creditAccountService = creditAccountService;
    }

    @GetMapping("/{id}")
    public CreditAccount getCreditAccountById(@PathVariable("id") UUID id) {
        return creditAccountService.findCreditAccountById(id);
    }

    @PutMapping("/{id}")
    public CreditAccount updateCreditAccountById(@PathVariable("id") UUID id,
            @Valid @RequestBody CreditAccount updatedCreditAccount) {
        CreditAccount creditAccount = creditAccountService.findCreditAccountById(id);
        if (creditAccount != null) {
            updatedCreditAccount.setAccountId(creditAccount.getAccountId());
            return creditAccountService.saveCreditAccount(updatedCreditAccount);
        }
        return null;
    }
}
