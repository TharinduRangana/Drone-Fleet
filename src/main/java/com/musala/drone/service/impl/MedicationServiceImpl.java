package com.musala.drone.service.impl;

import com.musala.drone.domain.request.RegisterMedicineRequest;
import com.musala.drone.domain.request.loadingmedi.LoadingMedicationDetailsRequest;
import com.musala.drone.domain.response.RegisterMedicationResponse;
import com.musala.drone.domain.response.loadedmedi.DroneMedicationDetails;
import com.musala.drone.domain.response.loadedmedi.LoadedMedicationDetails;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.LoadedMedication;
import com.musala.drone.entity.Medication;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.exceptions.DroneBatteryLowException;
import com.musala.drone.infrastructure.exceptions.DroneNotAvailableException;
import com.musala.drone.infrastructure.exceptions.DroneWeightLimitExceededException;
import com.musala.drone.infrastructure.util.ImageUtility;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.repository.LoadedMedicationRepository;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.service.LoadingMedicationValidationService;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private LoadingMedicationValidationService validationService;

    @Autowired
    private LoadedMedicationRepository loadedMedicationRepository;

    @Autowired
    private DroneRepository droneRepository;

    public RegisterMedicationResponse addNewMedicine(String name,Double weight, String code, MultipartFile image) throws IOException {

        RegisterMedicineRequest request = new RegisterMedicineRequest(name, weight, code);
        Medication medication = convertToMedicationObject(request, image);
        Medication savedMedication = medicationRepository.save(medication);
        return RegisterMedicationResponse.builder()
                .id(savedMedication.getId())
                .name(savedMedication.getName())
                .code(savedMedication.getCode())
                .weight(savedMedication.getWeight())
                .image(savedMedication.getImage())
                .build();

    }

    private Medication convertToMedicationObject(RegisterMedicineRequest request, MultipartFile image) throws IOException {
        Medication medication = new Medication();
        medication.setCode(request.getCode());
        medication.setName(request.getName());
        medication.setWeight(request.getWeight());
        medication.setImage(ImageUtility.compressImage(image.getBytes()));
        return medication;
    }

    public void loadingMedicationsToDrone(int droneId, List<LoadingMedicationDetailsRequest> request) {
        Drone selectedDrone = validationService.validateDrone(droneId);
        if (selectedDrone.getBatteryCapacity() < 25.0) {
            throw new DroneBatteryLowException("Drone Battery Is Very Low! Please Recharge!");
        }
        double totalMedication = selectedDrone.getLoadedWeight() + request.stream()
                .map(medic -> medic.getWeight())
                .reduce(0.0, Double::sum);

        if (totalMedication > selectedDrone.getWeightLimit()) {
            throw new DroneWeightLimitExceededException("Drone's Weight Limit Exceeded!");
        }
        List<Medication> medicationList = validationService.validateMedications(request);

        List<LoadedMedication> loadedMedicationList = new ArrayList<>();

        List<Medication> newMedicationList = new ArrayList<>();

        request.forEach(req ->{
            Medication medication = medicationList.stream().filter(x->x.getId().equals(req.getMedicationId())).findFirst().get();
            LoadedMedication loadedMedication = new LoadedMedication();
            loadedMedication.setDrone(selectedDrone);
            loadedMedication.setMedication(medication);
            loadedMedication.setMedicationWeight(req.getWeight());
            loadedMedication.setDelivered(false);
            loadedMedicationList.add(loadedMedication);
            medication.setWeight(medication.getWeight()- req.getWeight());
            newMedicationList.add(medication);
        });

        loadedMedicationRepository.saveAll(loadedMedicationList);
        selectedDrone.setLoadedWeight(totalMedication);
        if (totalMedication == selectedDrone.getWeightLimit()) {
            selectedDrone.setState(DroneStateEnum.LOADED);
        } else {
            selectedDrone.setState(DroneStateEnum.LOADING);
        }
        droneRepository.save(selectedDrone);
        medicationRepository.saveAll(newMedicationList);
    }

    public DroneMedicationDetails getLoadedMedicationsToADrone(int droneId) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();
            List<LoadedMedication> loadedMedicationList = loadedMedicationRepository.findAllByDrone_IdAndIsDelivered(droneId, false);
            LinkedHashMap<Medication, Double> loadedMedicationMap = new LinkedHashMap<>();
            loadedMedicationList.stream().forEach(loadedMedication -> {
                if (loadedMedicationMap.containsKey(loadedMedication.getMedication())) {
                    loadedMedicationMap.put(loadedMedication.getMedication(), loadedMedicationMap.get(loadedMedication.getMedication()) + loadedMedication.getMedicationWeight());
                } else {
                    loadedMedicationMap.put(loadedMedication.getMedication(), loadedMedication.getMedicationWeight());
                }
            });

            Set<Medication> keys = loadedMedicationMap.keySet();

            DroneMedicationDetails response = new DroneMedicationDetails();
            response.setDroneId(drone.getId());
            response.setDroneState(drone.getState());
            response.setDroneSerialNumber(drone.getSerialNumber());

            List<LoadedMedicationDetails> droneMedicationDetailList = new ArrayList<>();

            for (Medication key : keys) {
                LoadedMedicationDetails details = new LoadedMedicationDetails();
                details.setMedicationCode(key.getCode());
                details.setMedicationName(key.getName());
                details.setMedicationId(key.getId());
                details.setMedicationWeight(loadedMedicationMap.get(key));
                droneMedicationDetailList.add(details);
            }
            response.setMedicationList(droneMedicationDetailList);
            return response;

        } else {
            throw new DroneNotAvailableException("Requested Drone Is Not Available!");
        }
//        return null;
    }

}
