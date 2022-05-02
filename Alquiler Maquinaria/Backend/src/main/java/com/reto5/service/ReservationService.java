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
     * @return List<Reservation>
     */
    public List<Reservation> getAll(){
        return resRepository.getAll();
    }

    /**
     * GET select a specific id
     * @param reservationId reservationId
     * @return Optional<Reservation>
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return resRepository.getReservation(reservationId);
    }

    /**
     * POST
     * @param reservation reservation
     * @return Reservation
     */
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return resRepository.save(reservation);
        }else{
            Optional<Reservation> evt= resRepository.getReservation(reservation.getIdReservation());
            if(evt.isEmpty()){
                return resRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    /**
     * UPDATE
     * @param reservation reservation
     * @return Reservation
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> evt= resRepository.getReservation(reservation.getIdReservation());
            if(evt.isPresent()){

                if(reservation.getStartDate()!=null){
                    evt.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    evt.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    evt.get().setStatus(reservation.getStatus());
                }
                resRepository.save(evt.get());
                return evt.get();
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
