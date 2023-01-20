package com.vehicle.dto;

import com.vehicle.model.Message;
import com.vehicle.model.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientResponse {
    private String email;
    private String name;
    private Integer age;
    List<Message> messages;
    List<Reservation> reservations;
}
