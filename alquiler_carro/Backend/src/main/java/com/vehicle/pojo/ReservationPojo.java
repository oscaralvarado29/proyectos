package com.vehicle.pojo;

import java.util.Date;

import com.vehicle.model.Vehicle;
import com.vehicle.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ReservationPojo {
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;
    private Vehicle vehicle;
    private Client client;
}
