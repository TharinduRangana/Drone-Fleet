package com.musala.drone.service;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.RegisterDroneResponse;

public interface DroneService {
    public RegisterDroneResponse registerDrone(RegisterDroneRequest request);
}
