package com.reto5.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.reto5.intrface.ReservationInterface;
import com.reto5.model.Client;
import com.reto5.model.Reservation;
import com.reto5.report.ClientCount;
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
     * Select
     * @return List<Reservation>
     */
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationInterface.findAll();
    }

    /**
     * Select by id
     * @param id id
     * @return Optional<Reservation>
     */
    public Optional<Reservation> getReservation(int id){
        return reservationInterface.findById(id);
    }

    /**
     * Insert
     * @param reservation reservation
     * @return Reservation
     */
    public Reservation save(Reservation reservation){
        return reservationInterface.save(reservation);
    }

    /**
     * Delete
     * @param reservation reservation
     */
    public void delete(Reservation reservation){
        reservationInterface.delete(reservation);
    }

    public List<Reservation> getReservationByStatus(String status){
        return reservationInterface.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date a, Date b){
        return reservationInterface.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<ClientCount> getTopClients(){
        List<ClientCount> res= new ArrayList<>();
        List<Object[]> report = reservationInterface.countTotalReservationByClient();
        for (Object[] objects : report) {
            res.add(new ClientCount((Long) objects[1], (Client) objects[0]));
        }
        return res;
    }
}
