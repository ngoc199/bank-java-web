package com.bank.exceptions;

import java.util.UUID;

public class BankAccountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BankAccountNotFoundException(UUID id) {
        super("Bank account with " + id + " does not exist");
    }
}
