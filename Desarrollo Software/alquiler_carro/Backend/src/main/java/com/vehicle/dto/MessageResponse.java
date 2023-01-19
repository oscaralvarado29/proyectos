package com.vehicle.dto;

import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String messageText;
    private double score;
    private Vehicle vehicle;
    private Client client;
    private Reservation reservation;
}
