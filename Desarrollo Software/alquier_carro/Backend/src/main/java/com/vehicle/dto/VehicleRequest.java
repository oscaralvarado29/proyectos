package com.vehicle.dto;

import com.vehicle.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRequest {
    private String name;
    private String color;
    private String brand;
    private String model;
    private Integer horsePower;
    private Integer engineCylinders;
    private Integer seating;
    private Category category;
}
