package com.vehicle.pojo;

import com.vehicle.model.Category;
import com.vehicle.model.Message;
import com.vehicle.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VehiclePojo {
    private Integer idVehicle;
    private String name;
    private String color;
    private String brand;
    private String model;
    private Integer horsepower;
    private Integer engineCylinders;
    private Integer seating;
    private Category category;
    private List<Message> messages;
    private List<Reservation> reservations;

}
