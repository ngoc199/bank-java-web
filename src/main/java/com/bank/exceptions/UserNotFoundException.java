package com.bank.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(long id) {
        super("Could not find user " + id);
    }

    public UserNotFoundException(String username) {
        super("Username " + username + " does not exist");
    }

    public UserNotFoundException() {
        super("User does not exist");
    }
}
