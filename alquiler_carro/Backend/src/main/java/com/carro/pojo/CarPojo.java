package com.carro.pojo;

import com.carro.model.Category;
import com.carro.model.Message;
import com.carro.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarPojo {
    private Integer idCar;
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
