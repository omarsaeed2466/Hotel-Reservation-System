package com.example.hotelreservationsystem.exception;

public class CapacityFullException extends RuntimeException{

    public CapacityFullException(String message) {
        super(message);
    }
}
