package com.musala.drone.domain.request;

import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterDroneRequest {

    @Size(min=1, max = 100, message = "Incorrect Serial Number!")
    private String serialNumber;

    private DroneModelEnum model;

    @Max(value=500,message = "Weight limit exceeded!")
    private int weightLimit;

    @Min(value = 0)
    @Max(value = 100L, message = "incorrect battery")
    @Digits(integer=3, fraction=2)
    private double batteryCapacity;

    private DroneStateEnum state;
}
