package com.medicine.register.config;

import com.medicine.register.logger.ILoggerStrategy;
import com.medicine.register.logger.Log4jImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    @ConditionalOnProperty(name = "logger.impl",havingValue = "log4j")
    public ILoggerStrategy LogStrategy (){
        return new Log4jImpl();
    }
}
