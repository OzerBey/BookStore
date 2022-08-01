package com.ozer.bookstore.core.utilities.exceptions;

public class PhotoNotFoundException extends CustomException {

    public PhotoNotFoundException() {
        super("Photo Not Found !!");
    }

    public PhotoNotFoundException(String message) {
        super(message);
    }
}
