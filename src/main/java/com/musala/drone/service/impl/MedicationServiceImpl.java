package com.musala.drone.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.drone.domain.request.RegisterMedicineRequest;
import com.musala.drone.domain.response.RegisterMedicationResponse;
import com.musala.drone.entity.Medication;
import com.musala.drone.infrastructure.util.ImageUtility;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    public RegisterMedicationResponse addNewMedicine(String medicine, MultipartFile image) throws IOException {

        RegisterMedicineRequest request = convertIntoAddMedicineRequest(medicine);
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

    private RegisterMedicineRequest convertIntoAddMedicineRequest(String medicine) {

        RegisterMedicineRequest request = new RegisterMedicineRequest();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            request = objectMapper.readValue(medicine, RegisterMedicineRequest.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return request;
    }

    private Medication convertToMedicationObject(RegisterMedicineRequest request, MultipartFile image) throws IOException {
        Medication medication = new Medication();
        medication.setCode(request.getCode());
        medication.setName(request.getName());
        medication.setWeight(request.getWeight());
        medication.setImage(ImageUtility.compressImage(image.getBytes()));
        return medication;
    }

}
