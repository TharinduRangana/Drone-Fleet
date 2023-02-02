package com.musala.drone.service;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.DroneResponse;

import java.util.List;

public interface DroneService {
    public DroneResponse registerDrone(RegisterDroneRequest request);

    public List<DroneResponse> getAvailableDronesForLoading();

}
