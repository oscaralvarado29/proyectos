package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @Column()
    private Integer idReservation;
    @Column()
    private Date startDate;
    @Column()
    private Date devolutionDate;
    @Column()
    private String status = "created";

    @ManyToOne
    @JoinColumn(name = "idVehicle")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "idClient")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private Client client;

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "reservation")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private List<Message> messages;

    public Reservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
}
