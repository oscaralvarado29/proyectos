package com.vehicle.pojo;

import com.vehicle.model.Message;
import com.vehicle.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data                                           // Le dice a LOMBO que cree los getter y setters.
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos

public class ClientPojo {
    private Integer idClient;
    private String email;
    private String name;
    private Integer age;
    private List<Message> messages;
    private List<Reservation> reservations;
}
