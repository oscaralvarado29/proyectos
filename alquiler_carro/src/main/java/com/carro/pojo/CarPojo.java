package com.carro.pojo;

import com.carro.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPojo {
    private Integer idCar;
    private String color;
    private String branch;
    private String model;
    private Integer horsepower;
    private Integer engineCylinders;
    private Integer seating;
    private Category category;
}
