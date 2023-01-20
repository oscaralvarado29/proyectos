package com.vehicle.dto;

import com.vehicle.model.Category;
import com.vehicle.model.Message;
import com.vehicle.model.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleResponse {
    private String name;
    private String color;
    private String brand;
    private String model;
    private Integer horsePower;
    private Integer engineCylinders;
    private Integer seating;
    private Category category;
    private List<Reservation> reservations;
    private List<Message> messages;
}
