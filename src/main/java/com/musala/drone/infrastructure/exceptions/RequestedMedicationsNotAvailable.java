package com.musala.drone.infrastructure.exceptions;

public class RequestedMedicationsNotAvailable extends RuntimeException {

    public RequestedMedicationsNotAvailable(){}

    public RequestedMedicationsNotAvailable(String msg) {
        super(msg);
    }
}
