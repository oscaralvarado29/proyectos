package com.usa.hackaton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
@Table(name = "qualification")
public class Qualification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    @Column(length=250)
    private String messageText;
    @Column (length=2)
    private double score;

    @ManyToOne
    @JoinColumn(name="idBike")
    @JsonIgnoreProperties({"qualification", "user", "reservations"})
    private Bike bike;

    @ManyToOne
    @JoinColumn(name="idUsername")
    @JsonIgnoreProperties({"Qualification", "reservations", "user"})
    private User user;
}
