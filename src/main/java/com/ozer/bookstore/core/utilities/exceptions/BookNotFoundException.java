package com.ozer.bookstore.core.utilities.exceptions;

public class BookNotFoundException extends CustomException {

    public BookNotFoundException() {
        super("Book Not Found !!");
    }

    public BookNotFoundException(String message) {
        super(message);
    }

}
