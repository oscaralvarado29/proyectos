package com.vehicle.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.vehicle.intrface.ReservationInterface;
import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import com.vehicle.report.ClientCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar Alvarado
 */
@Repository
public class ReservationRepository {
    @Autowired
    private ReservationInterface reservationInterface;

    /**
     * get all
     * @return the call of the findAll method of the interface ReservationInterface
     */
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationInterface.findAll();
    }

    /**
     * get by specific id
     * @param reservationId reservation id to get
     * @return the call of the findById method of the interface ReservationInterface
     */
    public Optional<Reservation> getReservation(int reservationId){
        return reservationInterface.findById(reservationId);
    }

    /**
     * Insert
     * @param reservation objet with reservation data
     * @return the call of the save method of the interface ReservationInterface
     */
    public Reservation save(Reservation reservation){
        return reservationInterface.save(reservation);
    }

    /**
     * Delete
     * @param reservation objet with reservation data to delete
     */
    public void delete(Reservation reservation){
        reservationInterface.delete(reservation);
    }

    /**
     * get Reservation By Status
     * @param status
     * @return the call of the findAllByStatus method of the interface ReservationInterface
     */
    public List<Reservation> getReservationByStatus(String status){
        return reservationInterface.findAllByStatus(status);
    }

    /**
     * get Reservation for a Period
     * @param dateStart
     * @param dateFinish
     * @return the call of the findAllByStartDateAfterAndStartDateBefore method of the interface ReservationInterface
     */
    public List<Reservation> getReservationPeriod(Date dateStart, Date dateFinish){
        return reservationInterface.findAllByStartDateAfterAndStartDateBefore(dateStart, dateFinish);
    }

    /**
     * get Top Clients
     * @return reservationByClient
     */
    public List<ClientCount> getTopClients(){
        List<ClientCount> reservationByClient= new ArrayList<>();
        List<Object[]> report = reservationInterface.countTotalReservationByClient();
        for (Object[] objects : report) {
            reservationByClient.add(new ClientCount((Long) objects[1], (Client) objects[0]));
        }
        return reservationByClient;
    }
    public  List<Reservation> getReservationByClientAndVehicle (Client idClient, Vehicle idVehicle){
        return reservationInterface.findAllByClientAndVehicle(idClient, idVehicle);
    }
}
