package com.sparta.employee;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingMain {

    private static final Logger logger = Logger.getLogger("My Application Logger");

    public static Logger getLogger() {
        PropertyConfigurator.configure("log4j.properties");
        return logger;
    }



//        PropertyConfigurator.configure("log4j.properties");
//        logger.warn("Warning Message");
//        logger.trace("Trace Message");
//        logger.info("Info Message");
//        logger.error("Error Message");
//        logger.debug("Debug Message");
//        logger.fatal("Fatal Message");

}
