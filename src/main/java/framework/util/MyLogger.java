package framework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    public static Logger logger = LogManager.getLogger(MyLogger.class);

    public static void error(String message){
        logger.error(message);
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }
}
