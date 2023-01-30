package com.musala.drone.service;

import com.musala.drone.domain.response.RegisterMedicationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MedicationService {

    public RegisterMedicationResponse addNewMedicine(String medicine, MultipartFile image) throws IOException;

}