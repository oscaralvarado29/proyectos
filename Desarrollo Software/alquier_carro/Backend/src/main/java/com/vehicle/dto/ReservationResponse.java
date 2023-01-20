package com.vehicle.dto;

import com.vehicle.model.Client;
import com.vehicle.model.Message;
import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReservationResponse {
    private Date startDate;
    private Date devolutionDate;
    private String status;
    private Vehicle vehicle;
    private Client client;
    private List<Message> messages;
}
