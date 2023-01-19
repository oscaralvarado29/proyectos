package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Oscar Alvarado
 */

@SuppressWarnings("ALL")
@AllArgsConstructor                  // constructor con todos los argumentos
@NoArgsConstructor                   // constructor con todos los argumentos
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    @Column(length=250)
    private String messageText;
    @Column(length=4)
    private double score = -1.0;

    @ManyToOne
    @JoinColumn(name="idVehicle")
    @JsonIgnoreProperties({"messages", "client", "reservations", "category", "vehicle"})
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="idClient")
    @JsonIgnoreProperties({"messages", "reservations", "client", "category", "vehicle"})
    private Client client;

    @ManyToOne
    @JoinColumn(name="idReservation")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private Reservation reservation;
}
