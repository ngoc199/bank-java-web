package com.bank.services;

import java.util.List;

import com.bank.entities.Employee;
import com.bank.repositories.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Find all employees in the database
     * 
     * @return
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Find employee by id
     * 
     * @param id
     * @return employee
     */
    public Employee findEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }

    /**
     * Save employee to the database
     * 
     * @param employee
     * @return employee
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Delete employee by id
     * 
     * @param id
     */
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }
}
