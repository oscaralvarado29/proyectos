package com.carro.model;

import com.carro.pojo.ReservationPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status = "created";

    public Reservation(ReservationPojo reservationPojo) {
        this.idReservation = reservationPojo.getIdReservation();
        this.startDate = reservationPojo.getStartDate();
        this.devolutionDate = reservationPojo.getDevolutionDate();
        this.status = reservationPojo.getStatus();
        this.car = reservationPojo.getCar();
        this.client = reservationPojo.getClient();
        this.message = reservationPojo.getMessage();
    }

    @ManyToOne
    @JoinColumn(name = "idCar")
    @JsonIgnoreProperties("reservations")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "idClient")
    @JsonIgnoreProperties({"reservations","messages"})
    private Client client;


    @OneToOne(mappedBy = "reservation", optional = true)
    @JsonIgnoreProperties({"reservations","messages","client","car"})
    private Message message;

}
