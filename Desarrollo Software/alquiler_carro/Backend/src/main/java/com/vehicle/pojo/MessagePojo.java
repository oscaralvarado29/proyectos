package com.vehicle.pojo;

import com.vehicle.model.Vehicle;
import com.vehicle.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MessagePojo {
    private Integer idMessage;
    private String messageText;
    private double score;
    private Vehicle vehicle;
    private Client client;
}
