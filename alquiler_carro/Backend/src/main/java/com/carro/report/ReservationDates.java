package com.carro.report;

import com.carro.pojo.ReservationDatesPojo;
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

    public ReservationDates(ReservationDatesPojo reservationDatesPojo){
        this.dateStart = reservationDatesPojo.getDateStart();
        this.dateFinish = reservationDatesPojo.getDateFinish();
    }
}
