package com.carro.report;

import com.carro.model.Client;
import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientCount {
    private Long total;
    private Client client;
}
