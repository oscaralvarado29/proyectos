package com.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice()
public class ControllerAdvisor {
    private static final String MESSAGE = "message";

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> categoryNotFound( ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> categoryAlreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> clientNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> clientAlreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.CLIENT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Map<String, String>> messageNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.MESSAGE_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(MessageAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> messageAlreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.MESSAGE_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Map<String, String>> reservationNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESERVATION_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(DatesNotValidException.class)
    public ResponseEntity<Map<String, String>> datesNotValid(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(MESSAGE, ExceptionResponse.DATES_NOT_VALID.getMessage()));
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Map<String, String>> vehicleNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(MESSAGE, ExceptionResponse.VEHICLE_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> vehicleAlreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.VEHICLE_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(VehicleIsReservedException.class)
    public ResponseEntity<Map<String, String>> vehicleIsReserved(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(MESSAGE, ExceptionResponse.VEHICLE_IS_RESERVED.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> methodNotAllowed(HttpRequestMethodNotSupportedException messageError){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(Collections.singletonMap(MESSAGE, messageError.getMessage()));
    }

}
