package com.usa.hackaton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Oscar Alvarado
 */
@Data                                           // Le dice a LOMBO que cree los getter y setters.
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
@Entity
@Table(name = "sendRequest")
public class SendRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFriend;
    @Column(length=10)
    private String statusRequest;

    @ManyToOne
    @JoinColumn(name = "idUsername")
    @JsonIgnoreProperties("sendRequest")
    private User user;
}
