package com.vehicle.exception;

public enum ExceptionResponse {
    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_ALREADY_EXISTS("Category already exists"),
    CLIENT_NOT_FOUND("Client not found"),
    CLIENT_ALREADY_EXISTS("Client already exists"),
    MESSAGE_NOT_FOUND("Message not found"),
    MESSAGE_ALREADY_EXISTS("Message already exists"),
    RESERVATION_NOT_FOUND("Reservation not found"),
    DATES_NOT_VALID("Dates not valid"),
    VEHICLE_NOT_FOUND("Vehicle not found"),
    VEHICLE_ALREADY_EXISTS("Vehicle already exists"),
    VEHICLE_IS_RESERVED("Vehicle is reserved");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
