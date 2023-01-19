package com.vehicle.repository;

import com.vehicle.model.Client;
import com.vehicle.model.Message;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Optional<Message> findByMessageTextAndVehicleAndClientAndReservation(String messageText, Vehicle idVehicle, Client idClient, Reservation idReservation);
}
