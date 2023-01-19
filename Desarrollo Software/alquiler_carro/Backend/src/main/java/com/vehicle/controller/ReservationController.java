package com.vehicle.controller;

import java.text.ParseException;
import java.util.List;
import com.vehicle.dto.ReservationResponse;
import com.vehicle.dto.ReservationRequest;
import com.vehicle.dto.ReservationUpdate;
import com.vehicle.model.Client;
import com.vehicle.model.Vehicle;
import com.vehicle.report.ClientCount;
import com.vehicle.report.ReservationStatus;
import com.vehicle.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;

   @Operation(summary = "Get all the reservations")
   @ApiResponse(responseCode = "200", description = "All reservations returned",
           content = @Content(mediaType = "application/json",
                   array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class))))
    @GetMapping("/all")
   public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return ResponseEntity.ok(reservationService.getAllReservation());
    }

   @Operation(summary = "Get a reservation by his id")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Reservation found",
                   content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationResponse.class))),
           @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
   })
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable("id") int reservationId) {
        return ResponseEntity.ok(reservationService.getReservation(reservationId));
    }

   @Operation(summary = "Add a new reservation")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "201", description = "Reservation created", content = @Content),
           @ApiResponse(responseCode = "409", description = "Reservation already exists", content = @Content)
   })
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody ReservationRequest reservationRequest) {
        reservationService.saveReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

   @Operation(summary = "Update an existing reservation")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Reservation updated", content = @Content),
           @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
   })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ReservationUpdate reservationUpdate) {
      reservationService.updateReservation(reservationUpdate);
      return ResponseEntity.ok().build();
    }

   @Operation(summary = "Delete an existing reservation")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Reservation deleted", content = @Content),
           @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
   })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int reservationId) {
        reservationService.deleteReservation(reservationId);
      return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all the reservations by status")
    @ApiResponse(responseCode = "200", description = "All reservations by status returned",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ReservationStatus.class))))
    @GetMapping("/report-status")
    public ResponseEntity<ReservationStatus> getReservationsStatusReport(){
        return ResponseEntity.ok(reservationService.getReservationsStatusReport());
    }

    @Operation(summary = "Get all the reservations by a period of time")
    @ApiResponse(responseCode = "200", description = "All reservations returned by a period of time",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class))))
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<ReservationResponse> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) throws ParseException {
        return reservationService.getReservationPeriod(dateOne, dateTwo);
    }

    @Operation(summary = "Get amount of reservations by a client")
    @ApiResponse(responseCode = "200", description = "Get amount of reservations by a client",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientCount.class))))
    @GetMapping("report-clients")
    public ResponseEntity<List<ClientCount>> getAmountReservationsByClient(){
        return ResponseEntity.ok(reservationService.getTopClients());
    }
    @Operation(summary = "Get all the reservations by a client and vehicle")
    @ApiResponse(responseCode = "200", description = "All reservations returned by a client and vehicle",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ReservationResponse.class))))
    @GetMapping("/reservation-clients-vehicle/{idClient}/{idVehicle}")
    public List<ReservationResponse> getReservationByClientAndVehicle(@PathVariable("idClient") int idClient, @PathVariable("idVehicle") int idVehicle) {
       Client client = new Client(idClient);
       Vehicle vehicle = new Vehicle(idVehicle);
       return reservationService.getReservationByClientAndVehicle(client, vehicle);
   }
}
