package com.musala.drone.repository;

import com.musala.drone.entity.Drone;
import com.musala.drone.entity.enums.DroneStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Integer> {

    List<Drone> findAllByBatteryCapacityGreaterThanAndStateIn(double battery, List<DroneStateEnum> droneState);
}
