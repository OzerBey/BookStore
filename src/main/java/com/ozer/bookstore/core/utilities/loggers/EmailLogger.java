package com.ozer.bookstore.core.utilities.loggers;

public class EmailLogger implements ILogger {

    @Override
    public void log(String message) {
        System.err.println("Logged to email " + message);
    }
}
