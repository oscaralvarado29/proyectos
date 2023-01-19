package com.vehicle.repository;

import com.vehicle.model.Category;
import com.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    Optional<Vehicle> findVehicleByNameAndAndBrandAndCategory(String name, String brand, Category category);
}
