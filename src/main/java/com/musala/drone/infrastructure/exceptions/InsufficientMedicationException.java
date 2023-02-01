package com.musala.drone.infrastructure.exceptions;

public class InsufficientMedicationException extends RuntimeException {

    public InsufficientMedicationException() {}

    public InsufficientMedicationException(String msg){
        super(msg);
    }
}
