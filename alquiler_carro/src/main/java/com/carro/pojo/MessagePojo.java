package com.carro.pojo;

import com.carro.model.Car;
import com.carro.model.Client;
import com.carro.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MessagePojo {
    private Integer idMessage;
    private String messageText;
    private double score;
    private Car car;
    private Client client;
}
