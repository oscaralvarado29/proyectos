package com.reto5.report;

import com.reto5.model.Client;
import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */
@Data                                           // Le dice a LOMBO que cree los getter y setters.
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor

public class ClientCount {
    private Long total;
    private Client client;
}
