package com.bank.exceptions;

public class ExchangeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExchangeNotFoundException(long id) {
        super("Exchange " + id + " does not exist");
    }
}
