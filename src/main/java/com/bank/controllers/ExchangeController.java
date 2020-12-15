package com.bank.controllers;

import java.util.List;
import java.util.Map;

import com.bank.entities.BankAccount;
import com.bank.entities.Customer;
import com.bank.entities.Exchange;
import com.bank.services.ExchangeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/exchanges")
@RestController
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<Exchange> getAllExchanges(@RequestBody Customer customer) {
        if (customer != null) {
            return exchangeService.findExchangesByCustomer(customer);
        }
        return exchangeService.findAllExchanges();
    }

    @PostMapping
    public Exchange createNewExchange(@RequestBody Map<String, Object> request) {
        BankAccount sender = (BankAccount) request.get("sender");
        BankAccount receiver = (BankAccount) request.get("receiver");
        double amount = (double) request.get("amount");
        return exchangeService.createNewExchange(sender, receiver, amount);
    }

    @GetMapping("/{id}")
    public Exchange getExchangeById(@PathVariable("id") long id) {
        return exchangeService.findExchangeById(id);
    }

}
