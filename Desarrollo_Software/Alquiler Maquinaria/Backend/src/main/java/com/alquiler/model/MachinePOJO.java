package com.alquiler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class MachinePOJO {
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String description;
    private Category category;
    private List<Message> messages;
    private List<Reservation> reservations;

}
