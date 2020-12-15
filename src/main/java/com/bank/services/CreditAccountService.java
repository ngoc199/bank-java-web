package com.bank.services;

import java.util.UUID;

import javax.transaction.Transactional;

import com.bank.entities.CreditAccount;
import com.bank.entities.Department;
import com.bank.exceptions.BankAccountNotFoundException;
import com.bank.exceptions.EmployeeNotFoundException;
import com.bank.repositories.CreditAccountRepository;
import com.bank.repositories.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final EmployeeRepository employeeRepository;

    public CreditAccountService(CreditAccountRepository creditAccountRepository,
            EmployeeRepository employeeRepository) {
        this.creditAccountRepository = creditAccountRepository;
        this.employeeRepository = employeeRepository;
    }

    public CreditAccount findCreditAccountById(UUID id) {
        return creditAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public CreditAccount createNewCreditAccount(CreditAccount creditAccount, long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            if (employee.getDepartment().equals(Department.SALE)) {
                return creditAccountRepository.save(creditAccount);
            }
            return null;
        }).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Transactional(rollbackOn = Exception.class)
    public CreditAccount saveCreditAccount(CreditAccount creditAccount) {
        return creditAccountRepository.save(creditAccount);
    }

}
