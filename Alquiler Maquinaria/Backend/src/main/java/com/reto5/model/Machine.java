package com.reto5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

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

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine", "client"})
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "machine")
    @JsonIgnoreProperties({"machine", "messages"})
    private List<Reservation> reservations;

    public Machine(MachineDTO machineDTO) {
        this.id = machineDTO.getId();
        this.name = machineDTO.getName();
        this.brand = machineDTO.getBrand();
        this.year = machineDTO.getYear();
        this.description = machineDTO.getDescription();
        this.category = machineDTO.getCategory();
        this.messages = machineDTO.getMessages();
        this.reservations = machineDTO.getReservations();
    }
}
