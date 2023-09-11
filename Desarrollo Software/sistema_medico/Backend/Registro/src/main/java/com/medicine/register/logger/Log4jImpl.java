package com.medicine.register.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jImpl implements ILoggerStrategy {

    private static Logger logger = LoggerFactory.getLogger(Log4jImpl.class);

    @Override
    public void logInfo(String msg) {
        logger.info(msg);
    }

    @Override
    public void logError(String msg) {
        logger.error(msg);
    }

}