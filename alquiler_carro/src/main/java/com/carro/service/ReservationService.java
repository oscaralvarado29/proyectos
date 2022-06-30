package com.carro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.carro.model.Reservation;
import com.carro.report.ClientCount;
import com.carro.report.ReservationStatus;
import com.carro.repository.ReservationRepository;
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
     * GET ALL
     * @return  the call of the getAll method of the class ReservationRepository
     */
    public List<Reservation> getAll(){
        return resRepository.getAll();
    }

    /**
     * GET by specific id
     * @param reservationId category id to get
     * @return the call of the getReservation method of the class ReservationRepository
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return resRepository.getReservation(reservationId);
    }

    /**
     * POST
     * @param reservation object with reservation data
     * @return the call of the save method of the class ReservationRepository if the reservation id don´t exist or is empty else return to reservation
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
     * @param reservation object with reservation data
     * @return the call of the update method of the class ReservationRepository if the reservation exist else return to reservation
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
     * @return true if the category is deleted else return false
     */
    public boolean deleteReservation(Integer reservationId) {
        return getReservation(reservationId).map(reservation -> {
            resRepository.delete(reservation);
            return true;
        }).orElse(false);
    }

    /**
     * GET select reservation for status
     * @return ReservationStatus objet with the number of completed and canceled reservations
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
     * @return the call of getReservationPeriod method of the class ReservationRepository if dateA is before to dateB else return an empty arraylist
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
     * @return the call of getTopClients method of the class ReservationRepository
     */
    public List<ClientCount> getTopClients(){
        return resRepository.getTopClients();
    }
}
