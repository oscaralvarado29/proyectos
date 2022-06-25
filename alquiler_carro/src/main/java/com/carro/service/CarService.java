package com.carro.service;

import com.carro.model.Car;
import com.carro.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    /**
     * GET ALL
     * @return  the call of the getAll method of the class carRepository
     */
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    /**
     * GET by specific id
     * @param carId car id to get
     * @return the call of the getCategory method of the class carRepository
     */
    public Optional<Car> getCar (int carId){
        return carRepository.getCar(carId);
    }

    /**
     * POST
     * @param car object with car data
     * @return the call of the save method of the class carRepository if the category id don´t exist or is empty else return to car
     */
    public Car save(Car car){
        if (car.getIdCar() == null) {
            return carRepository.save(car);
        }else {
        Optional<Car> carData = carRepository.getCar(car.getIdCar());
        if (carData.isEmpty()){
            return carRepository.save(car);
        }else {
            return car;
        }
        }
    }

    /**
     * UPDATE
     * @param car object with car data
     * @return the call of the save method of the class carRepository if the category exist else return to car
     */
    public Car update (Car car) {
        if (car.getIdCar() != null){
            Optional<Car> carData = carRepository.getCar(car.getIdCar());
            if (carData.isPresent()){
                if (car.getColor() != null) {
                    carData.get().setColor(car.getColor());
                }
                if (car.getBranch() != null) {
                    carData.get().setBranch(car.getBranch());
                }
                if (car.getModel() != null) {
                    carData.get().setModel(car.getModel());
                }
                if (car.getHorsepower() != null) {
                    carData.get().setHorsepower(car.getHorsepower());
                }
                if (car.getEngineCylinders() != null) {
                    carData.get().setEngineCylinders(car.getEngineCylinders());
                }
                if (car.getSeating() != null) {
                    carData.get().setSeating(car.getSeating());
                }
                return carRepository.save(carData.get());
            }
        }
        return car;
    }

    /**
     * DELETE
     * @param carId car id to delete
     * @return true if the car is deleted else return false
     */
    public boolean deleteCar(Integer carId){
        return getCar(carId).map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }
}
