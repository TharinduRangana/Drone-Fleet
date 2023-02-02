package com.musala.drone.domain.response.loadedmedi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadedMedicationDetails {

    private int medicationId;

    private String medicationName;

    private String medicationCode;

    private double medicationWeight;
}
