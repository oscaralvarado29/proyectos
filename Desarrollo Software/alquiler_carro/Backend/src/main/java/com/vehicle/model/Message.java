package com.vehicle.model;

import com.vehicle.pojo.MessagePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */

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

    public Message(MessagePojo messagePojo) {
        this.idMessage = messagePojo.getIdMessage();
        this.messageText = messagePojo.getMessageText();
        this.score = messagePojo.getScore();
        this.vehicle = messagePojo.getVehicle();
        this.client = messagePojo.getClient();
        this.reservation=messagePojo.getReservation();
    }

    @ManyToOne
    @JoinColumn(name="idVehicle")
    @JsonIgnoreProperties({"messages", "client", "reservations"})
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="idClient")
    @JsonIgnoreProperties({"messages", "reservations", "client"})
    private Client client;

    @ManyToOne
    @JoinColumn(name="idReservation")
    @JsonIgnoreProperties({"messages", "reservations", "client", "vehicle"})
    private Reservation reservation;
}
