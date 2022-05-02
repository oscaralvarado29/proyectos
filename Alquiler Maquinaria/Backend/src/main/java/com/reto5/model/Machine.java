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
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
@Entity
@Table(name = "machine")
public class Machine implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=45)
    private String name;
    @Column(length=45)
    private String brand;
    @Column(length=4)
    private Integer year;
    @Column(length=250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("machines")
    private Category category;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine", "client"})
    private List<Message> messages;

    @Column(nullable=true)
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine", "messages"})
    private List<Reservation> reservations;
}
