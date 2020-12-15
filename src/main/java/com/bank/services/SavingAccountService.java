package com.bank.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.bank.entities.Department;
import com.bank.entities.Employee;
import com.bank.entities.SavingAccount;
import com.bank.repositories.EmployeeRepository;
import com.bank.repositories.SavingAccountRepository;

import org.springframework.stereotype.Service;

@Service
public class SavingAccountService {
    private final SavingAccountRepository savingAccountRepository;
    private final EmployeeRepository employeeRepository;

    public SavingAccountService(SavingAccountRepository savingAccountRepository,
            EmployeeRepository employeeRepository) {
        this.savingAccountRepository = savingAccountRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Find all saving accounts in the database
     * 
     * @return listSavingAccounts
     */
    public List<SavingAccount> findAllSavingAccounts() {
        return savingAccountRepository.findAll();
    }

    public SavingAccount findSavingAccountById(UUID id) {
        return savingAccountRepository.findById(id).get();
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean createSavingAccount(SavingAccount savingAccount, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        if (employee.getDepartment().equals(Department.SALE)) {
            return savingAccountRepository.save(savingAccount) != null;
        }
        return false;
    }

    @Transactional(rollbackOn = Exception.class)
    public SavingAccount saveSavingAccount(SavingAccount savingAccount) {
        return savingAccountRepository.save(savingAccount);
    }
}
