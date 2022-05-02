package com.reto5.model;

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
@NoArgsConstructor
@Entity
@Table(name = "")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @Column(name="email",nullable=false,length=45,unique=true)  // con name le puedo cambiar el nombre de abajo para mapearlo diferente, nulleable false dice que el campo no puede ser nulo, le puedo decir que sea unico.
    private String email;
    @Column(length=45)
    private String password;
    @Column(length=250)
    private String name;
    @Column(length=2)
    private Integer age;
    @Column(length=2)
    private Integer nee;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties("client")
    private List<Message>messages;
    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    @JsonIgnoreProperties("client")
    private List<Reservation>reservations;
}
