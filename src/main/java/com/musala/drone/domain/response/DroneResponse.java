package com.musala.drone.domain.response;

import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DroneResponse {

    private Integer id;

    private String serialNumber;

    private DroneModelEnum model;

    private int weightLimit;

    private double batteryCapacity;

    private DroneStateEnum state;
}
