package com.bank.services;

import java.util.List;

import javax.transaction.Transactional;

import com.bank.entities.BankAccount;
import com.bank.entities.Customer;
import com.bank.entities.Exchange;
import com.bank.exceptions.ExchangeNotFoundException;
import com.bank.repositories.BankAccountRepository;
import com.bank.repositories.ExchangeRepository;

import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final BankAccountRepository bankAccountRepository;

    public ExchangeService(ExchangeRepository exchangeRepository, BankAccountRepository bankAccountRepository) {
        this.exchangeRepository = exchangeRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<Exchange> findAllExchanges() {
        return exchangeRepository.findAll();
    }

    public List<Exchange> findExchangesByCustomer(Customer customer) {
        return exchangeRepository.findByCustomer(customer);
    }

    public Exchange findExchangeById(long id) {
        return exchangeRepository.findById(id).orElseThrow(() -> new ExchangeNotFoundException(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public Exchange createNewExchange(BankAccount sender, BankAccount receiver, double amount) {
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);
        Exchange newExchange = new Exchange(sender, receiver, amount);
        return exchangeRepository.save(newExchange);
    }
}
