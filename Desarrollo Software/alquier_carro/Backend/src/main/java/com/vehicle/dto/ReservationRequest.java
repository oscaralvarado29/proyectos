package com.vehicle.dto;

import com.vehicle.model.Client;
import com.vehicle.model.Vehicle;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ReservationRequest {
    private Date startDate;
    private Date devolutionDate;
    private String status;
    private Vehicle vehicle;
    private Client client;
}
