package com.vehicle.dto;

import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageRequest {
    private String messageText;
    private double score;
    private Vehicle vehicle;
    private Client client;
    private Reservation reservation;
}
