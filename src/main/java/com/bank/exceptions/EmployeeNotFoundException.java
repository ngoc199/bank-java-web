package com.bank.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(long id) {
        super("Employee id " + id + " does not exist");
    }
}
