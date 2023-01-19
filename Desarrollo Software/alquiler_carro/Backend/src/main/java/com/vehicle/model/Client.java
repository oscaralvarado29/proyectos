package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Oscar Alvarado
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @Column(name="email",nullable=false,length=45,unique=true)  // con name le puedo cambiar el nombre de abajo para mapearlo diferente, nulleable false dice que el campo no puede ser nulo, le puedo decir que sea unico.
    private String email;
    @Column(length=250)
    private String name;
    @Column(length=2)
    private Integer age;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private List<Message>messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties({"messages", "category", "client", "vehicle", "reservations"})
    private List<Reservation>reservations;

    public Client(Integer idClient) {
        this.idClient = idClient;
    }
}
