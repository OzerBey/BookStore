package com.ozer.bookstore.core.utilities.exceptions;

public class AuthorNotFoundException extends CustomException {

    public AuthorNotFoundException() {
        super("Author Not Found !!");
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
