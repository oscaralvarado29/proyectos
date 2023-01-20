package com.vehicle.controller;

import java.util.List;
import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.dto.ClientUpdate;
import com.vehicle.service.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar Alvarado
 */
@RestController
@RequestMapping("/Client")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Get all the clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All clients returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class)))),
    })
    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> getClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Operation(summary = "Get a client by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getAllClient(@PathVariable("id") int clientId) {
        return ResponseEntity.ok(clientService.getClient(clientId));
    }

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Client already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody ClientRequest clientRequest) {
        clientService.saveClient(clientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ClientUpdate clientUpdate) {
        clientService.updateClient(clientUpdate);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete an existing category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
