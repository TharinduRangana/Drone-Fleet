package com.musala.drone.domain.response.loadedmedi;

import com.musala.drone.entity.enums.DroneStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DroneMedicationDetails {

    private int droneId;

    private String droneSerialNumber;

    private List<LoadedMedicationDetails> medicationList;

    private DroneStateEnum droneState;

}
