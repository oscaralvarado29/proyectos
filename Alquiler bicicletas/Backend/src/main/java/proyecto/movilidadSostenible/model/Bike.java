package com.usa.hackaton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Oscar Alvarado
 */
@Data                                           // Le dice a LOMBO que cree los getter y setters.
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
@Entity
@Table(name = "bike")
public class Bike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBike;
    @Column(length=45)
    private String brand;
    @Column(length=4)
    private Integer year;
    @Column(length=250)
    private String description;
    @Column(length=12)
    private String status ="created";

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "bike")
    @JsonIgnoreProperties({"bike", "user"})
    private List<Qualification> qualification;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "bike")
    @JsonIgnoreProperties({"bike", "qualification"})
    private List<Reservation> reservations;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bike_id_user")
    private User bike;

    public User getBike() {
        return bike;
    }

    public void setBike(User bike) {
        this.bike = bike;
    }
}
