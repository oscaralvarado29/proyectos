package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.vehicle.pojo.ClientPojo;
import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */

@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor
@Getter
@Setter                              // Le dice a LOMBO que cree un constructor sin argumentos
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

    public Client(ClientPojo clientPojo) {
        this.idClient = clientPojo.getIdClient();
        this.email = clientPojo.getEmail();
        this.name = clientPojo.getName();
        this.age = clientPojo.getAge();
        this.messages = clientPojo.getMessages();
        this.reservations = clientPojo.getReservations();
    }

    public Client(int idClient){
        this.idClient=idClient;
    }

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties("client")
    private List<Message>messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties("client")
    private List<Reservation>reservations;
}
