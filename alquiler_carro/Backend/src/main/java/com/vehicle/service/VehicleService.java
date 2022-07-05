package com.vehicle.service;

import com.vehicle.model.Vehicle;
import com.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * GET ALL
     * @return  the call of the getAll method of the class VehicleRepository
     */
    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    /**
     * GET by specific id
     * @param vehicleId vehicle id to get
     * @return the call of the getCategory method of the class VehicleRepository
     */
    public Optional<Vehicle> getVehicle (int vehicleId){
        return vehicleRepository.getVehicle(vehicleId);
    }

    /**
     * POST
     * @param vehicle object with vehicle data
     * @return the call of the save method of the class VehicleRepository if the vehicle id don´t exist or is empty else return to vehicle
     */
    public Vehicle save(Vehicle vehicle){
        if (vehicle.getIdVehicle() == null) {
            return vehicleRepository.save(vehicle);
        }else {
        Optional<Vehicle> vehicleSave = vehicleRepository.getVehicle(vehicle.getIdVehicle());
        if (vehicleSave.isEmpty()){
            return vehicleRepository.save(vehicle);
        }else {
            return vehicle;
        }
        }
    }

    /**
     * UPDATE
     * @param vehicle object with vehicle data
     * @return the call of the update method of the class VehicleRepository if the category exist else return to vehicle
     */
    public Vehicle update (Vehicle vehicle) {
        if (vehicle.getIdVehicle() != null){
            Optional<Vehicle> vehicleUpdate = vehicleRepository.getVehicle(vehicle.getIdVehicle());
            if (vehicleUpdate.isPresent()){
                if (vehicle.getName() != null) {
                    vehicleUpdate.get().setName(vehicle.getName());
                }
                if (vehicle.getColor() != null) {
                    vehicleUpdate.get().setColor(vehicle.getColor());
                }
                if (vehicle.getBrand() != null) {
                    vehicleUpdate.get().setBrand(vehicle.getBrand());
                }
                if (vehicle.getModel() != null) {
                    vehicleUpdate.get().setModel(vehicle.getModel());
                }
                if (vehicle.getHorsepower() != null) {
                    vehicleUpdate.get().setHorsepower(vehicle.getHorsepower());
                }
                if (vehicle.getEngineCylinders() != null) {
                    vehicleUpdate.get().setEngineCylinders(vehicle.getEngineCylinders());
                }
                if (vehicle.getSeating() != null) {
                    vehicleUpdate.get().setSeating(vehicle.getSeating());
                }
                return vehicleRepository.save(vehicleUpdate.get());
            }
        }
        return vehicle;
    }

    /**
     * DELETE
     * @param vehicleId vehicle id to delete
     * @return true if the vehicle is deleted else return false
     */
    public boolean deleteVehicle(Integer vehicleId){
        return getVehicle(vehicleId).map(vehicle -> {
            vehicleRepository.delete(vehicle);
            return true;
        }).orElse(false);
    }
}
