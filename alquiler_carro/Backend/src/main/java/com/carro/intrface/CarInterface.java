package com.carro.intrface;

import com.carro.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarInterface extends CrudRepository<Car,Integer> {
}
