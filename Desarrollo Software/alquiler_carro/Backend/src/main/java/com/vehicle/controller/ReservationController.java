package com.vehicle.controller;

import java.util.List;
import java.util.Optional;

import com.vehicle.model.Client;
import com.vehicle.model.Reservation;
import com.vehicle.model.Vehicle;
import com.vehicle.pojo.ReservationPojo;
import com.vehicle.report.ClientCount;
import com.vehicle.report.ReservationStatus;
import com.vehicle.service.ReservationService;
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
     * @param dateOne
     * @param dateTwo
     * @return the call of the getReservationPeriod method of the class ReservationService
    */
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
        return reservationService.getReservationPeriod(dateOne, dateTwo);
    }

    /**
     * Reservations report by clients
     * @return the call of the getTopClients method of the class ReservationService
     */
    @GetMapping("/report-clients")
    public List<ClientCount> getReservationsReportsClient(){
        return reservationService.getTopClients();
    }

    @GetMapping("/reservation-clients-vehicle/{idClient}/{idVehicle}")
    public List<Reservation> getReservationsByClientAndVehicle(@PathVariable("idClient") int idClient, @PathVariable("idVehicle") int idVehicle){
        Client client =new Client(idClient);
        Vehicle vehicle = new Vehicle(idVehicle);
        return reservationService.getReservationByClientAndVehicle(client, vehicle);
    }
}
