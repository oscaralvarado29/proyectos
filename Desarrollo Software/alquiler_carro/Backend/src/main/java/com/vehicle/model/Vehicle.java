package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.SuppressAjWarnings;

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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oscar Alvarado
 */

@SuppressWarnings("ALL")
@SuppressAjWarnings
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehicle;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String color;
    @Column(length = 45)
    private String brand;
    @Column(length = 45)
    private String model;
    @Column(length = 4)
    private Integer horsePower;
    @Column(length = 3)
    private Integer engineCylinders;
    @Column(length = 3)
    private Integer seating;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private Category category;

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "vehicle")
    @JsonIgnoreProperties("vehicle")
    private List<Message> messages;

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "vehicle")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private List<Reservation> reservations;

    public Vehicle(Integer idVehicle) {
        this.idVehicle = idVehicle;
    }
}
