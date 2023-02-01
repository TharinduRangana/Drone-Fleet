package com.musala.drone.infrastructure.exceptions;

public class DroneIsNotAvailableException extends RuntimeException {

    public DroneIsNotAvailableException(){}

    public DroneIsNotAvailableException(String msg) {
        super(msg);
    }
}
