package com.vehicle.report;

import lombok.*;

/**
 *
 * @author Oscar Alvarado
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatus {
    private int completed;
    private int cancelled;
}
