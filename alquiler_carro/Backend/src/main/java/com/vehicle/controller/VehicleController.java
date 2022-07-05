package com.vehicle.controller;

import com.vehicle.model.Vehicle;
import com.vehicle.pojo.VehiclePojo;
import com.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Vehicle")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    /**
     * GET all
     * @return the call of the getAll method of the class VehicleService
     */
    @GetMapping("/all")
    public List<Vehicle> getVehicle(){
        return vehicleService.getAll();
    }

    /**
     * GET by specific id
     * @param vehicleId vehicle id to get
     * @return the call of the getVehicle method of the class VehicleService
     */
    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicle(@PathVariable("id") int vehicleId) {
        return vehicleService.getVehicle(vehicleId);
    }

    /**
     * POST
     * @param vehiclePojo pojo created with vehicle data
     * @return the call of the save method of the class VehicleService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle save(@RequestBody VehiclePojo vehiclePojo) {
        Vehicle vehicle = new Vehicle(vehiclePojo);
        return vehicleService.save(vehicle);
    }

    /**
     * PuT
     * @param vehiclePojo pojo created with vehicle data
     * @return the call of the update method of the class VehicleService
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle update(@RequestBody VehiclePojo vehiclePojo) {
        Vehicle vehicle = new Vehicle(vehiclePojo);
        return vehicleService.update(vehicle);
    }

    /**
     * DELETE
     * @param vehiclePojo vehicle id to delete
     * @return the call of the delete method of the class VehicleService
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody VehiclePojo vehiclePojo) {
        Vehicle vehicle = new Vehicle(vehiclePojo);
        return vehicleService.deleteVehicle(vehicle.getIdVehicle());
    }
}
