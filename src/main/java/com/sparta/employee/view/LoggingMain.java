package com.sparta.employee.view;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingMain {

    private static final Logger logger = Logger.getLogger("My Application Logger");

    public static Logger getLogger() {
        PropertyConfigurator.configure("log4j.properties");
        return logger;
    }
}
