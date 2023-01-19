package com.vehicle.controller;

import java.util.List;
import com.vehicle.dto.MessageRequest;
import com.vehicle.dto.MessageResponse;
import com.vehicle.dto.MessageUpdate;
import com.vehicle.service.IMessageService;
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
@RequestMapping("/Message")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequiredArgsConstructor
public class MessageController {
    private final IMessageService messageService;

    @Operation(summary = "Get all the messages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All messages returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
    })
    @GetMapping("/all")
    public ResponseEntity<List<MessageResponse>> getAllMessages(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @Operation(summary = "Get a message by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Message not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessage(@PathVariable("id") int messageId) {
        return ResponseEntity.ok(messageService.getMessage(messageId));
    }

    @Operation(summary = "Add a new message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Message already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Message not found", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody MessageUpdate messageUpdate) {
        messageService.updateMessage(messageUpdate);
        return ResponseEntity.ok().build();
    }

        @Operation(summary = "Delete an existing category")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Category deleted", content = @Content),
                @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
        })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
