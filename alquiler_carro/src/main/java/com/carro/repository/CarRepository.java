package com.carro.repository;

import com.carro.intrface.CarInterface;
import com.carro.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class CarRepository {
    @Autowired
    private CarInterface carInterface;

    /**
     * get all
     * @return the call of the findAll method of the interface carInterface
     */
    public List<Car> getAll(){
        return (List<Car>) carInterface.findAll();
    }

    /**
     * get by specific id
     * @param carId car id to get
     * @return the call of the findById method of the interface carInterface
     */
    public Optional<Car> getCar(int carId) {
        return carInterface.findById(carId);
    }

    /**
     * Insert
     * @param car objet with car data
     * @return the call of the save method of the interface carInterface
     */
    public Car save(Car car) {
        return carInterface.save(car);
    }

    /**
     * Delete
     * @param car objet with car data to delete
     */
    public void delete(Car car) {
        carInterface.delete(car);
    }
}
