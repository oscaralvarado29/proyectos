package com.alquiler.controller;

import java.util.List;
import java.util.Optional;
import com.alquiler.model.Reservation;
import com.alquiler.model.ReservationPOJO;
import com.alquiler.report.ClientCount;
import com.alquiler.report.ReservationStatus;
import com.alquiler.service.ReservationService;
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
     * @return reservationService.getAll()
     */
    @GetMapping("/all")
    public List<Reservation> getReservations(){
        return reservationService.getAll();
    }

    /**
     * GET for specific id
     * @param reservationId id of reservation
     * @return reservationService.getReservation(reservationId)
     */
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return reservationService.getReservation(reservationId);
    }

    /**
     * post
     * @param reservationDTO POJO of reservation
     * @return reservationService.save(reservation)
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody ReservationPOJO reservationDTO) {
        Reservation reservation = new Reservation(reservationDTO);
        return reservationService.save(reservation);
    }

    /**
     * PUT
     * @param reservationDTO POJO of reservation
     * @return reservationService.update(reservation)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody ReservationPOJO reservationDTO) {
        Reservation reservation = new Reservation(reservationDTO);
        return reservationService.update(reservation);
    }

    /**
     * DELETE
     * @param reservationId id of reservation
     * @return reservationService.deleteReservation(reservationId)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

    @GetMapping("/report-status")
    public ReservationStatus getReservationsStatusReport(){
        return reservationService.getReservationsStatusReport();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
        return reservationService.getReservationPeriod(dateOne, dateTwo);
    }

    @GetMapping("/report-clients")
    public List<ClientCount> getReservationsReportsClient(){
        return reservationService.getTopClients();
    }
}
