package com.medicine.register.logger;

public interface ILoggerStrategy {
    void logInfo(String msg);
    void logError(String msg);
}
