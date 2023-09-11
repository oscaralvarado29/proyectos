package com.medicine.register.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.medicine.register.dao.repository") // Reemplaza con el paquete de tus repositorios JPA
@EntityScan(basePackages = "com.medicine.register.dao.entity") // Reemplaza con el paquete de tus entidades JPA
public class JpaConfig {
}


