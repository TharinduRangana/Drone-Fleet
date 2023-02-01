package com.musala.drone.service;

import com.musala.drone.domain.request.loadingmedi.LoadingMedicationDetailsRequest;
import com.musala.drone.domain.request.loadingmedi.LoadingMedicationRequest;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;

import java.util.List;

public interface LoadingMedicationValidationService {

    public Drone validateDrone(int id);

    public List<Medication> validateMedications(List<LoadingMedicationDetailsRequest> request);

}
