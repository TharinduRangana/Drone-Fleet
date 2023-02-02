package com.musala.drone.service.impl;

import com.musala.drone.domain.request.loadingmedi.LoadingMedicationDetailsRequest;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.exceptions.DroneNotAvailableException;
import com.musala.drone.infrastructure.exceptions.RequestedMedicationsNotAvailable;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.service.LoadingMedicationValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoadingMedicationValidationServiceImpl implements LoadingMedicationValidationService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public Drone validateDrone(int id) {
        Optional<Drone> drone = droneRepository.findById(id);
        if (drone.isPresent() && (drone.get().getState().equals(DroneStateEnum.IDLE) || (drone.get().getState().equals(DroneStateEnum.LOADING)))) {
            return drone.get();
        } else {
            throw new DroneNotAvailableException("Requested Drone Is Not Available At This Moment!");
        }
    }

    public List<Medication> validateMedications(List<LoadingMedicationDetailsRequest> request) {

        List<Integer> medicationIdList = new ArrayList<>();
        request.stream().forEach(medication -> {
            medicationIdList.add(medication.getMedicationId());
        });

        List<Medication> medicationList = medicationRepository.findByIds(medicationIdList);

        medicationList.stream().forEach(medication -> {
            request.forEach(req -> {
                if (req.getCode().equals(medication.getCode()) && req.getWeight()>medication.getWeight()){
                    throw new RequestedMedicationsNotAvailable("Requested Medication Weight Limit Is Not Available At The Store Now!");
                }
            });
        });
        return medicationList;
    }


}
