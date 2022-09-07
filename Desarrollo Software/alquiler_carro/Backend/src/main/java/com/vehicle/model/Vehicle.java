package com.vehicle.model;

import com.vehicle.pojo.VehiclePojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oscar Alvarado
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
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

    public Vehicle(VehiclePojo vehiclePojo) {
        this.idVehicle = vehiclePojo.getIdVehicle();
        this.name = vehiclePojo.getName();
        this.color = vehiclePojo.getColor();
        this.brand = vehiclePojo.getBrand();
        this.model = vehiclePojo.getModel();
        this.horsePower = vehiclePojo.getHorsePower();
        this.engineCylinders = vehiclePojo.getEngineCylinders();
        this.seating = vehiclePojo.getSeating();
        this.category = vehiclePojo.getCategory();
        this.messages = vehiclePojo.getMessages();
        this.reservations = vehiclePojo.getReservations();
    }

    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties("vehicle")
    private Category category;

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "vehicle")
    @JsonIgnoreProperties("vehicle")
    private List<Message> messages;

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "vehicle")
    @JsonIgnoreProperties("vehicle")
    private List<Reservation> reservations;
}
