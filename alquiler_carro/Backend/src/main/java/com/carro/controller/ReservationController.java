package com.carro.controller;

import java.util.List;
import java.util.Optional;
import com.carro.model.Reservation;
import com.carro.pojo.ReservationDatesPojo;
import com.carro.pojo.ReservationPojo;
import com.carro.report.ClientCount;
import com.carro.report.ReservationDates;
import com.carro.report.ReservationStatus;
import com.carro.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /**
     * GET
     * @return the call of the getAll method of the class ReservationService
     */
    @GetMapping("/all")
    public List<Reservation> getReservations(){
        return reservationService.getAll();
    }

    /**
     * GET for specific id
     * @param reservationId reservation id to get
     * @return the call of the getAll method of the class ReservationService
     */
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return reservationService.getReservation(reservationId);
    }

    /**
     * POST
     * @param reservationPojo pojo created with reservation data
     * @return the call of the save method of the class ReservationService
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody ReservationPojo reservationPojo) {
        Reservation reservation = new Reservation(reservationPojo);
        return reservationService.save(reservation);
    }

    /**
     * PuT
     * @param reservationPojo pojo created with reservation data
     * @return the call of the update method of the class ReservationService
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody ReservationPojo reservationPojo) {
        Reservation reservation = new Reservation(reservationPojo);
        return reservationService.update(reservation);
    }

    /**
     * DELETE
     * @param reservationPojo reservation id to delete
     * @return the call of the deleteCategory method of the class ReservationService
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@RequestBody ReservationPojo reservationPojo) {
        Reservation reservation = new Reservation(reservationPojo);
        return reservationService.deleteReservation(reservation.getIdReservation());
    }

    /**
     * Reservations report by status
     * @return the call of the getReservationsStatusReport method of the class ReservationService
     */
    @GetMapping("/report-status")
    public ReservationStatus getReservationsStatusReport(){
        return reservationService.getReservationsStatusReport();
    }

    /**
     * Reservations report by dates
     * @param reservationDatesPojo pojo created with reservation dates
     * @return the call of the getReservationPeriod method of the class ReservationService
     */
    @GetMapping("/report-dates")
    public List<Reservation> getReservationsReportDates(@RequestBody ReservationDatesPojo reservationDatesPojo){
        ReservationDates reservationDates = new ReservationDates(reservationDatesPojo);
        return reservationService.getReservationPeriod(reservationDates.getDateStart(), reservationDates.getDateFinish());
    }

    /**
     * Reservations report by clients
     * @return the call of the getTopClients method of the class ReservationService
     */
    @GetMapping("/report-clients")
    public List<ClientCount> getReservationsReportsClient(){
        return reservationService.getTopClients();
    }
}
