package com.vehicle.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ReservationUpdate {
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;
}
