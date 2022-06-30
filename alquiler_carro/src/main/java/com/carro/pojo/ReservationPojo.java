package com.carro.pojo;

import java.util.Date;

import com.carro.model.Car;
import com.carro.model.Client;
import com.carro.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ReservationPojo {
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;
    private Car car;
    private Client client;
    private Message message;
}
