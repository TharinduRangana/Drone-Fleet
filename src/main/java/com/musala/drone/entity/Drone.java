package com.musala.drone.entity;

import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.converter.DroneModelEnumConverter;
import com.musala.drone.infrastructure.converter.DroneStateEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    public Drone(String serialNumber, DroneModelEnum model, int weightLimit, double batteryCapacity, DroneStateEnum state, double loadedWeight) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.loadedWeight = loadedWeight;
    }

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

    @Column(name = "loadedWeight")
    private double loadedWeight;
}
