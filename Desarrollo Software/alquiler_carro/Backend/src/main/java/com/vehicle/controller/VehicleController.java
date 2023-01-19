package com.vehicle.controller;

import com.vehicle.dto.VehicleRequest;
import com.vehicle.dto.VehicleResponse;
import com.vehicle.dto.VehicleUpdate;
import com.vehicle.service.IVehicleService;
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

import java.util.List;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Vehicle")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
public class VehicleController {

    private final IVehicleService vehicleService;

    @Operation(summary = "Get all the vehicles")
    @ApiResponse(responseCode = "200", description = "All vehicles returned",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = VehicleResponse.class))))
    @GetMapping("/all")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @Operation(summary = "Get a vehicle by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable("id") int vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicle(vehicleId));
    }

    @Operation(summary = "Add a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Vehicle already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody VehicleRequest vehicleRequest) {
        vehicleService.saveVehicle(vehicleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Update an existing vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody VehicleUpdate vehicleUpdate) {
        vehicleService.updateVehicle(vehicleUpdate);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete an existing vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
