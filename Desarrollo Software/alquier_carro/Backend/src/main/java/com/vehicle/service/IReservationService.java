package com.vehicle.service;

import com.vehicle.dto.*;
import com.vehicle.model.Client;
import com.vehicle.model.Vehicle;
import com.vehicle.report.ClientCount;
import com.vehicle.report.ReservationStatus;
import java.text.ParseException;
import java.util.List;

public interface IReservationService {
    List<ReservationResponse> getAllReservation();
    ReservationResponse getReservation (int reservationId);
    void saveReservation(ReservationRequest reservationRequest);
    void deleteReservation(int reservationId);
    void updateReservation(ReservationUpdate reservationUpdate);
    ReservationStatus getReservationsStatusReport();
    List<ReservationResponse> getReservationPeriod(String dateOne, String dateTwo) throws ParseException;
    List<ClientCount> getTopClients();
    List<ReservationResponse> getReservationByClientAndVehicle(Client client, Vehicle vehicle);
}
