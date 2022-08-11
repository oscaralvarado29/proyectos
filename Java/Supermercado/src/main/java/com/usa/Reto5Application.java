package com.usa;

import com.usa.controlller.Controller;
import com.usa.model.ProductRepository;
import com.usa.view.WindowUpdate;
import com.usa.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@ComponentScan("com.usa.model")
@EnableJdbcRepositories("com.usa.model")

public class Reto5Application {

    @Autowired
    ProductRepository repositorio;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Reto5Application.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            View view = new View();
            WindowUpdate windowUpdate = new WindowUpdate();
            Controller controlador = new Controller(repositorio, view, windowUpdate);
        };
    }
}

