package com.alquiler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
@Table(name = "message")
public class Message implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    @Column(length=250)
    private String messageText;

    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnoreProperties({"messages", "client", "reservations"})
    private Machine machine;

    @ManyToOne
    @JoinColumn(name="idClient")
    @JsonIgnoreProperties({"messages", "reservations", "client"})
    private Client client;

    public Message(MessagePOJO messagePOJO) {
        this.idMessage = messagePOJO.getIdMessage();
        this.messageText = messagePOJO.getMessageText();
        this.machine = messagePOJO.getMachine();
        this.client = messagePOJO.getClient();
    }
}
