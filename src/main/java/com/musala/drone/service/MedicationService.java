package com.musala.drone.service;

import com.musala.drone.domain.request.loadingmedi.LoadingMedicationDetailsRequest;
import com.musala.drone.domain.request.loadingmedi.LoadingMedicationRequest;
import com.musala.drone.domain.response.RegisterMedicationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MedicationService {

    public RegisterMedicationResponse addNewMedicine(String name,Double weight, String code, MultipartFile image) throws IOException;

    public void loadingMedicationsToDrone(int droneId, List<LoadingMedicationDetailsRequest> request);
}
