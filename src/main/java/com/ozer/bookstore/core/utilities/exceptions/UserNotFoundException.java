package com.ozer.bookstore.core.utilities.exceptions;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("User Not Found !!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
