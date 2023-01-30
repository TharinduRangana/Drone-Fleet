package com.musala.drone.entity;

import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.converter.DroneModelEnumConverter;
import com.musala.drone.infrastructure.converter.DroneStateEnumConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Drone {

    @Id
    @GeneratedValue(generator = "drone_gen", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "drone_gen",sequenceName = "drone_seq", initialValue = 1,allocationSize = 100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "droneSerialNumber")
    private String serialNumber;

    @Column(name = "droneModel")
    @Convert(converter = DroneModelEnumConverter.class )
    private DroneModelEnum model;

    @Column(name = "droneWeightLimit")
    private int weightLimit;


    @Column(name = "droneBatteryCapacity")
    private double batteryCapacity;

    @Column(name = "droneState")
    @Convert(converter = DroneStateEnumConverter.class )
    private DroneStateEnum state;
}
