package com.musala.drone.infrastructure.exceptions;

public class DroneBatteryLowException extends RuntimeException {

    public DroneBatteryLowException(){}

    public DroneBatteryLowException(String msg) {
        super(msg);
    }
}
