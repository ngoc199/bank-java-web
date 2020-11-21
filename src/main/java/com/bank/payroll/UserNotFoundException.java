package com.bank.payroll;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(long id) {
        super("Could not find user " + id);
    }
}
