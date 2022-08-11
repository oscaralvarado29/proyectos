package com.vehicle.repository;

import com.vehicle.intrface.VehicleInterface;
import com.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class VehicleRepository {
    @Autowired
    private VehicleInterface vehicleInterface;

    /**
     * get all
     * @return the call of the findAll method of the interface VehicleInterface
     */
    public List<Vehicle> getAll(){
        return (List<Vehicle>) vehicleInterface.findAll();
    }

    /**
     * get by specific id
     * @param vehicleId vehicle id to get
     * @return the call of the findById method of the interface VehicleInterface
     */
    public Optional<Vehicle> getVehicle(int vehicleId) {
        return vehicleInterface.findById(vehicleId);
    }

    /**
     * Insert
     * @param vehicle objet with vehicle data
     * @return the call of the save method of the interface VehicleInterface
     */
    public Vehicle save(Vehicle vehicle) {
        return vehicleInterface.save(vehicle);
    }

    /**
     * Delete
     * @param vehicle objet with vehicle data to delete
     */
    public void delete(Vehicle vehicle) {
        vehicleInterface.delete(vehicle);
    }
}
