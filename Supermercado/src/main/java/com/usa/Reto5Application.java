package com.usa;

import com.usa.controlador.Controlador;
import com.usa.modelo.ProductoRepository;
import com.usa.vista.VentanaActualizar;
import com.usa.vista.Vista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@ComponentScan("com.usa.modelo")
@EnableJdbcRepositories("com.usa.modelo")

public class Reto5Application {

    @Autowired
    ProductoRepository repositorio;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Reto5Application.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            Vista vista = new Vista();
            VentanaActualizar ventanaActualizar= new VentanaActualizar();
            Controlador controlador = new Controlador(repositorio, vista, ventanaActualizar);
        };
    }
}

