package com.vehicle.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Oscar Alvarado
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDates {
    private String dateStart;
    private String dateFinish;

}
