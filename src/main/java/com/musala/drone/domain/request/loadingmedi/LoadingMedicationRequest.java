package com.musala.drone.domain.request.loadingmedi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoadingMedicationRequest {

    List<LoadingMedicationDetailsRequest> medicationList;
}
