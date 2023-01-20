package com.vehicle.mapper;

import com.vehicle.dto.ReservationRequest;
import com.vehicle.dto.ReservationResponse;
import com.vehicle.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {
    Reservation toReservation(ReservationRequest reservationRequest);
    default ReservationResponse toReservationResponse(Reservation reservation){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setStartDate(reservation.getStartDate());
        reservationResponse.setDevolutionDate(reservation.getDevolutionDate());
        reservationResponse.setStatus(reservation.getStatus());
        reservationResponse.setVehicle(reservation.getVehicle());
        reservationResponse.setClient(reservation.getClient());
        reservationResponse.setMessages(reservation.getMessages());
        return reservationResponse;
    }
    default List<ReservationResponse> toReservationResponseList(List<Reservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationResponses.add(toReservationResponse(reservation));
        }
        return reservationResponses;
    }
}
