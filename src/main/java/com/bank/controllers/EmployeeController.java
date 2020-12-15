package com.bank.controllers;

import javax.validation.Valid;

import com.bank.entities.Employee;
import com.bank.services.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/api/employees")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createNewEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee putMethodName(@PathVariable("id") long id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            updatedEmployee.setId(employee.getId());
            return employeeService.saveEmployee(updatedEmployee);
        }
        return null;
    }

}
