package com.usa.hackaton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Oscar Alvarado
 */
@Data                                           // Le dice a LOMBO que cree los getter y setters.
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer idUser;
    @Column(length=250)
    private String name;
    @Column(length=2)
    private Integer age;
    @Column(length=10)
    private Integer phoneNumber;
    @Column(length=50)
    private String address;
    @Column(length=50)
    private String neighborhood;
    @Column(length=10)
    private String gender;


    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="bike")
    @JsonIgnoreProperties("user")
    private List<Bike>bike;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "user")
    @JsonIgnoreProperties("user")
    private List<Reservation>reservations;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="SendRequest")
    @JsonIgnoreProperties("user")
    private List<SendRequest>sendRequest;

}
