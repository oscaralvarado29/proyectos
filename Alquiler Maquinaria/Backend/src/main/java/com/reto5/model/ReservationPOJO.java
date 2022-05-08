package com.reto5.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ReservationPOJO {
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status="created";
    private String score;
    private Machine machine;
    private Client client;
}
