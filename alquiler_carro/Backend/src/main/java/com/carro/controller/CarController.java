package com.carro.controller;

import com.carro.model.Car;
import com.carro.pojo.CarPojo;
import com.carro.service.CarService;
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
@RequestMapping("/Car")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class CarController {
    @Autowired
    private CarService carService;

    /**
     * GET all
     * @return the call of the getAll method of the class carService
     */
    @GetMapping("/all")
    public List<Car> getCar(){
        return carService.getAll();
    }

    /**
     * GET by specific id
     * @param carId car id to get
     * @return the call of the getCar method of the class carService
     */
    @GetMapping("/{id}")
    public Optional<Car> getCar(@PathVariable("id") int carId) {
        return carService.getCar(carId);
    }

    /**
     * POST
     * @param carPojo pojo created with car data
     * @return the call of the save method of the class carService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Car save(@RequestBody CarPojo carPojo) {
        Car car = new Car(carPojo);
        return carService.save(car);
    }

    /**
     * PuT
     * @param carPojo pojo created with car data
     * @return the call of the update method of the class carService
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Car update(@RequestBody CarPojo carPojo) {
        Car car = new Car(carPojo);
        return carService.update(car);
    }

    /**
     * DELETE
     * @param carPojo car id to delete
     * @return the call of the delete method of the class carService
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody CarPojo carPojo) {
        Car car = new Car(carPojo);
        return carService.deleteCar(car.getIdCar());
    }
}
