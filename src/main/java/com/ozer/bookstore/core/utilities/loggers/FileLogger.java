package com.ozer.bookstore.core.utilities.loggers;

public class FileLogger implements ILogger {

    @Override
    public void log(String message) {
        System.err.println("Logged to file " + message);
    }
}
