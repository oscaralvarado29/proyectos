package com.vehicle.intrface;

import com.vehicle.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleInterface extends CrudRepository<Vehicle,Integer> {
}
