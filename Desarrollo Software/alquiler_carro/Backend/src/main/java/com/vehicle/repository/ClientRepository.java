package com.vehicle.repository;

import com.vehicle.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    boolean existsByName(String name);
}
