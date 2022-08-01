package com.ozer.bookstore.core.utilities.loggers;

public class DatabaseLogger implements ILogger {

    @Override
    public void log(String message) {
        System.err.println("Logged to database " + message);
    }
}
