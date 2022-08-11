package com.vehicle.report;

import com.vehicle.model.Client;
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
