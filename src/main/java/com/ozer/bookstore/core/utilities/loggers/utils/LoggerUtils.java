package com.ozer.bookstore.core.utilities.loggers.utils;

import com.ozer.bookstore.core.utilities.loggers.ILogger;

public class LoggerUtils { // to run the multiple logger
    public static void runLoggers(ILogger[] loggers, String message) {
        for (ILogger logger : loggers) {
            logger.log(message);
        }
    }
}
