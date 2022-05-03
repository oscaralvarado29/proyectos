package com.reto5.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.reto5.model.Reservation;
import com.reto5.report.ClientCount;
import com.reto5.report.ReservationStatus;
import com.reto5.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar Alvarado
 */
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository resRepository;

    /**
     * GET
     * @return resRepository.getAll()
     */
    public List<Reservation> getAll(){
        return resRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param reservationId reservationId
     * @return resRepository.getReservation(reservationId)
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return resRepository.getReservation(reservationId);
    }

    /**
     * POST
     * @param reservation reservation
     * @return resRepository.save(reservation) if reservation.getIdReservation() is null or
     * reservation1 is empty else return reservation
     */
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return resRepository.save(reservation);
        }else{
            Optional<Reservation> reservation1= resRepository.getReservation(reservation.getIdReservation());
            if(reservation1.isEmpty()){
                return resRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    /**
     * UPDATE
     * @param reservation reservation to update
     * @return reservationUpdate.get() if reservation.getIdReservation() is not null and reservationUpdate is
     * not empty else return reservation
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> reservationUpdate= resRepository.getReservation(reservation.getIdReservation());
            if(reservationUpdate.isPresent()){

                if(reservation.getStartDate()!=null){
                    reservationUpdate.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    reservationUpdate.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    reservationUpdate.get().setStatus(reservation.getStatus());
                }
                if(reservation.getScore()!=null){
                    reservationUpdate.get().setScore(reservation.getScore());
                }
                resRepository.save(reservationUpdate.get());
                return reservationUpdate.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    /**
     * DELETE
     * @param reservationId reservationId
     * @return boolean
     */
    public boolean deleteReservation(Integer reservationId) {
        return getReservation(reservationId).map(reservation -> {
            resRepository.delete(reservation);
            return true;
        }).orElse(false);
    }

    /**
     * GET select reservation for status
     * @return ReservationStatus
     */
    public ReservationStatus getReservationsStatusReport(){
        List<Reservation>completed= resRepository.getReservationByStatus("completed");
        List<Reservation>cancelled= resRepository.getReservationByStatus("cancelled");
        return new ReservationStatus(completed.size(), cancelled.size());
    }

    /**
     * GET select reservations for a specific period of time
     * @param dateA dateA
     * @param dateB dateB
     * @return List<Reservation>
     */
    public List<Reservation> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date aDate= new Date();
        Date bDate= new Date();

        try {
            aDate = parser.parse(dateA);
            bDate = parser.parse(dateB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }
        if(aDate.before(bDate)){
            return resRepository.getReservationPeriod(aDate, bDate);
        }else{
            return new ArrayList<>();
        }

    }

    /**
     * GET select clients by classification
     * @return List<ClientCount>
     */
    public List<ClientCount> getTopClients(){
        return resRepository.getTopClients();
    }
}
