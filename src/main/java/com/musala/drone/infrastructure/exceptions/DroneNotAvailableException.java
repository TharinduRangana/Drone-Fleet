package com.musala.drone.infrastructure.exceptions;

public class DroneNotAvailableException extends RuntimeException {

    public DroneNotAvailableException(){}

    public DroneNotAvailableException(String msg) {
        super(msg);
    }
}
