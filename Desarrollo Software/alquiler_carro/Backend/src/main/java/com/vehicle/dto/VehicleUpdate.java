package com.vehicle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleUpdate {
    private Integer idVehicle;
    private String name;
    private String color;
    private String brand;
    private String model;
    private Integer horsePower;
    private Integer engineCylinders;
    private Integer seating;
}
