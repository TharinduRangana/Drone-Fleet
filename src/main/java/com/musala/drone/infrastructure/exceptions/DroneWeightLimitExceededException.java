package com.musala.drone.infrastructure.exceptions;

public class DroneWeightLimitExceededException extends RuntimeException {
    public DroneWeightLimitExceededException(){}

    public DroneWeightLimitExceededException(String msg) {
        super(msg);
    }
}
